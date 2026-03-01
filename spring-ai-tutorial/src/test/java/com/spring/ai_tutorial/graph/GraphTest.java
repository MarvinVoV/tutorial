package com.spring.ai_tutorial.graph;

import com.alibaba.cloud.ai.graph.CompiledGraph;
import com.alibaba.cloud.ai.graph.KeyStrategy;
import com.alibaba.cloud.ai.graph.KeyStrategyFactory;
import com.alibaba.cloud.ai.graph.StateGraph;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import com.alibaba.cloud.ai.graph.state.strategy.AppendStrategy;
import com.alibaba.cloud.ai.graph.state.strategy.ReplaceStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static com.alibaba.cloud.ai.graph.StateGraph.END;
import static com.alibaba.cloud.ai.graph.StateGraph.START;
import static com.alibaba.cloud.ai.graph.action.AsyncEdgeAction.edge_async;
import static com.alibaba.cloud.ai.graph.action.AsyncNodeAction.node_async;

@SpringBootTest
class GraphTest {

    @Autowired
    public ChatModel chatModel;

    @Test
    void testSimpleGraph() throws Exception {

        // 1. 定义 State 的 key 合并策略（对应 LangGraph 的 TypedDict）
        KeyStrategyFactory keyStrategyFactory = () -> {
            Map<String, KeyStrategy> strategies = new HashMap<>();
            strategies.put("input", new ReplaceStrategy());   // 覆盖
            strategies.put("output", new ReplaceStrategy());
            strategies.put("messages", new AppendStrategy()); // 追加
            return strategies;
        };

        // 2. 定义节点（对应 LangGraph 的 node 函数）
        NodeAction step1 = state -> {
            String input = (String) state.value("input").orElse("");
            System.out.println("Step1 收到：" + input);
            return Map.of("output", "step1处理了：" + input);
        };

        NodeAction step2 = state -> {
            String prev = (String) state.value("output").orElse("");
            ChatClient chatClient = ChatClient.builder(chatModel).build();
            String aiReply = chatClient.prompt()
                    .user("用一句话总结：" + prev)
                    .call()
                    .content();
            System.out.println("Step2 AI回复：" + aiReply);
            return Map.of("output", aiReply);
        };

        // 3. 构建图（对应 LangGraph 的 StateGraph）
        StateGraph graph = new StateGraph(keyStrategyFactory)
                .addNode("step1", node_async(step1))
                .addNode("step2", node_async(step2))
                .addEdge(START, "step1")
                .addEdge("step1", "step2")
                .addEdge("step2", END);

        // 4. 编译
        CompiledGraph compiled = graph.compile();

        // 5. 运行
        Map<String, Object> initialState = Map.of("input", "Spring AI Alibaba Graph 真不错");

        compiled.stream(initialState)
                .doOnNext(output -> System.out.println("节点输出：" + output))
                .blockLast();
    }

    @Test
    public void testGraphWithFunction() throws Exception {
        // 路由节点：判断去哪
        NodeAction router = state -> {
            String input = (String) state.value("input").orElse("");
            String next = input.contains("天气") ? "weather_node" : "chat_node";
            return Map.of("next", next);
        };

        NodeAction weatherNode = state -> {
            System.out.println("走天气分支");
            return Map.of("output", "今天天气晴，25度");
        };

        NodeAction chatNode = state -> {
            System.out.println("走聊天分支");
            return Map.of("output", "你好，有什么可以帮你的？");
        };

        KeyStrategyFactory ksf = () -> {
            Map<String, KeyStrategy> s = new HashMap<>();
            s.put("input", new ReplaceStrategy());
            s.put("output", new ReplaceStrategy());
            s.put("next", new ReplaceStrategy());
            return s;
        };

        StateGraph graph = new StateGraph(ksf)
                .addNode("router", node_async(router))
                .addNode("weather_node", node_async(weatherNode))
                .addNode("chat_node", node_async(chatNode))
                .addEdge(START, "router")
                // 条件路由，读取 state 里的 next 决定走哪条边
                .addConditionalEdges("router",
                        edge_async(state -> (String) state.value("next").orElse("chat_node")),
                        Map.of(
                                "weather_node", "weather_node",
                                "chat_node", "chat_node"
                        ))
                .addEdge("weather_node", END)
                .addEdge("chat_node", END);

        CompiledGraph compiled = graph.compile();

// 测试天气路由
        compiled.stream(Map.of("input", "今天天气怎么样"))
                .doOnNext(o -> System.out.println(o))
                .blockLast();

// 测试聊天路由
        compiled.stream(Map.of("input", "你好啊"))
                .doOnNext(o -> System.out.println(o))
                .blockLast();
    }
}