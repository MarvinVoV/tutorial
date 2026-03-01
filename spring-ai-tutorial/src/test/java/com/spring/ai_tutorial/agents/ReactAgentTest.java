package com.spring.ai_tutorial.agents;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.RunnableConfig;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.agent.hook.Hook;
import com.alibaba.cloud.ai.graph.agent.hook.hip.HumanInTheLoopHook;
import com.alibaba.cloud.ai.graph.agent.hook.hip.ToolConfig;
import com.alibaba.cloud.ai.graph.agent.hook.modelcalllimit.ModelCallLimitHook;
import com.alibaba.cloud.ai.graph.checkpoint.savers.MemorySaver;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ReactAgentTest {

    @Autowired
    private ChatModel chatModel;

    @Test
    void testSimpleReactAgent() throws Exception {

        // 1. 定义工具
        ToolCallback searchTool = FunctionToolCallback
                .builder("search_web", (String query) -> {
                    System.out.println(">>> 搜索工具被调用，query=" + query);
                    // 模拟搜索结果
                    return "搜索结果：Spring AI 最新版本是 1.1.2，发布于2025年";
                })
                .description("搜索网络上的最新信息")
                .inputType(String.class)
                .build();

        ToolCallback calcTool = FunctionToolCallback
                .builder("calculate", (String expr) -> {
                    System.out.println(">>> 计算工具被调用，expr=" + expr);
                    // 模拟计算
                    return "计算结果：42";
                })
                .description("执行数学计算")
                .inputType(String.class)
                .build();


        // 2. 创建 ReactAgent
        ReactAgent agent = ReactAgent.builder()
                .name("my_agent")
                .model(chatModel)
                .tools(searchTool, calcTool)
                .systemPrompt("你是一个智能助手，需要时调用工具获取信息，然后给出准确回答")
                .saver(new MemorySaver())  // 支持多轮记忆
                .build();

        // 3. 运行
        AssistantMessage response = agent.call("Spring AI 最新版本是什么？");
        System.out.println("回答：" + response.getText());
    }

    @Test
    void testMultiTurnReactAgent() throws Exception {

        ToolCallback weatherTool = FunctionToolCallback
                .builder("get_weather", (String city) -> {
                    System.out.println(">>> 天气工具被调用，city=" + city);
                    return city + " 今天晴天，28°C";
                })
                .description("获取指定城市的天气")
                .inputType(String.class)
                .build();

        ReactAgent agent = ReactAgent.builder()
                .name("weather_agent")
                .model(chatModel)
                .tools(weatherTool)
                .systemPrompt("你是天气助手")
                .saver(new MemorySaver())
                .build();

        // 同一个 threadId = 同一个会话，AI 能记住上下文
        RunnableConfig config = RunnableConfig.builder()
                .threadId("user-session-001")
                .build();

        AssistantMessage r1 = agent.call("北京今天天气怎么样？", config);
        System.out.println("第一轮：" + r1.getText());

        AssistantMessage r2 = agent.call("那上海呢？", config);
        System.out.println("第二轮：" + r2.getText());

        // AI 应该记得刚才问过北京
        AssistantMessage r3 = agent.call("两个城市温度差多少度？", config);
        System.out.println("第三轮：" + r3.getText());
    }

    @Test
    void testInvokeReactAgent() throws Exception {
        // 给 agent 加上 ModelCallLimitHook 限制最多调用 3 次，防止无限循环
        ModelCallLimitHook hook = ModelCallLimitHook.builder()
                .runLimit(3)
                .exitBehavior(ModelCallLimitHook.ExitBehavior.ERROR)
                .build();

        ReactAgent agent = ReactAgent.builder()
                .name("my_agent")
                .model(chatModel)
                .systemPrompt("你是一个助手")
                .hooks(hook)
                .saver(new MemorySaver())
                .build();

        Optional<OverAllState> result = agent.invoke("用一句话介绍 Spring Boot");

        result.ifPresent(state -> {
            // 查看所有消息历史（包括中间推理步骤）
            List<Message> messages = state.value("messages", new ArrayList<>());
            System.out.println("共 " + messages.size() + " 条消息");
            messages.forEach(msg ->
                    System.out.println("[" + msg.getMessageType() + "] " + msg.getText())
            );
        });
    }
}