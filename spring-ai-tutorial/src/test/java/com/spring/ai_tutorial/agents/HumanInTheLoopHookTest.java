package com.spring.ai_tutorial.agents;

import com.alibaba.cloud.ai.graph.NodeOutput;
import com.alibaba.cloud.ai.graph.RunnableConfig;
import com.alibaba.cloud.ai.graph.action.InterruptionMetadata;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.agent.hook.hip.HumanInTheLoopHook;
import com.alibaba.cloud.ai.graph.agent.hook.hip.ToolConfig;
import com.alibaba.cloud.ai.graph.checkpoint.savers.MemorySaver;
import com.alibaba.cloud.ai.graph.utils.TypeRef;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
class HumanInTheLoopHookTest {

    @Autowired
    private ChatModel chatModel;


    @Test
    void testHumanInTheLoopWithTerminalInput() throws Exception {

        // 1. 定义工具，加上明显的日志
        ToolCallback sendEmailTool = FunctionToolCallback
                .builder("send_email", (String content) -> {
                    System.out.println(">>> [工具执行] 发送邮件，内容：" + content);
                    return "邮件已成功发送：" + content;
                })
                .description("发送邮件给用户")
                .inputType(String.class)
                .build();

        ToolCallback queryTool = FunctionToolCallback
                .builder("query_info", (String query) -> {
                    System.out.println(">>> [工具执行] 查询信息：" + query);
                    return "查询结果：用户张三，账户余额1000元，邮箱 zhangsan@example.com";
                })
                .description("查询指定用户的账户信息，包括余额和邮箱，查询前必须调用此工具")
                .inputType(String.class)
                .build();

        // 2. 配置 HumanInTheLoopHook，只对 send_email 要求人工审批
        HumanInTheLoopHook hook = HumanInTheLoopHook.builder()
                .approvalOn("send_email", ToolConfig.builder()
                        .description("发送邮件，请人工确认")
                        .build())
                .build();

        // 3. 创建 Agent
        MemorySaver saver = new MemorySaver();
        ReactAgent agent = ReactAgent.builder()
                .name("email_agent")
                .model(chatModel)
                .tools(sendEmailTool, queryTool)
                .systemPrompt("你是邮件助手。\n" +
                        "                当用户要发邮件时，你必须：\n" +
                        "                1. 先调用 query_info 工具查询用户信息\n" +
                        "                2. 再调用 send_email 工具发送邮件\n" +
                        "                任何情况下都不能跳过这两个步骤。")
                .hooks(hook)
                .saver(saver)
                .build();

        String threadId = "session-" + System.currentTimeMillis();
        RunnableConfig config = RunnableConfig.builder()
                .threadId(threadId)
                .build();

        Scanner scanner = new Scanner(System.in);

        // 4. 第一次运行，遇到 send_email 会触发 hook 暂停
        System.out.println("\n=== 开始执行 Agent ===");
        System.out.println("任务：先查询张三的信息，然后给他发一封余额提醒邮件\n");

        try {
            // 使用 invokeAndGetOutput 来获取可能的中断信息
            Optional<NodeOutput> result = agent.invokeAndGetOutput(
                    "请调用工具查询张三（ID: zhangsan）的最新账户余额，然后发送余额提醒邮件给他,Email地址: zhangsan@qq.com",
                    config
            );
// 检查是否返回了中断元数据
            if (result.isPresent() && result.get() instanceof InterruptionMetadata) {
                InterruptionMetadata interruption = (InterruptionMetadata) result.get();
                System.out.println("\n⚠️  Agent 暂停等待审批");
                System.out.println("中断详情: " + interruption.toString());

                // 遍历工具调用请求信息
                for (InterruptionMetadata.ToolFeedback feedback : interruption.toolFeedbacks()) {
                    System.out.println("需要审批的工具: " + feedback.getName());
                    System.out.println("调用参数: " + feedback.getArguments());
                    System.out.println("描述: " + feedback.getDescription());
                }

                // 5. 终端等待人工输入
                System.out.println("\n请输入审批结果：");
                System.out.print("是否允许发送邮件？(y/n): ");
//                String input = scanner.nextLine().trim().toLowerCase();
                String input = "y"; // 模拟审批通过

                // 构建审批反馈
                Object approvalMetadata;
                if ("y".equals(input)) {
                    System.out.println("\n✅ 审批通过，继续执行...\n"); // todo 下面示例默认是批准所有
                    approvalMetadata = buildApprovalMetadata(interruption);
                } else {
                    System.out.println("\n❌ 审批拒绝，任务终止");
                    approvalMetadata = buildApprovalMetadata(interruption);
                }

                // 使用相同的 threadId 和审批反馈继续执行
                RunnableConfig resumeConfig = RunnableConfig.builder()
                        .threadId(threadId)
                        .addMetadata(RunnableConfig.HUMAN_FEEDBACK_METADATA_KEY, approvalMetadata)
                        .build();

                // 第二次调用以恢复执行
                Optional<NodeOutput> finalResult = agent.invokeAndGetOutput("", resumeConfig);

                if (finalResult.isPresent()) {
                    System.out.println("\n最终回复：" + finalResult.get().toString());
                }
            } else {
                // 如果没有中断，直接结束
                System.out.println("\n执行完成，回复：" + result.map(Object::toString).orElse("无结果"));
            }
        } catch (Exception e) {
            System.err.println("执行出错: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();


    }
    private static InterruptionMetadata buildApprovalMetadata(InterruptionMetadata interruption) {
        InterruptionMetadata.Builder builder = InterruptionMetadata.builder()
                .nodeId(interruption.node())
                .state(interruption.state());

        // 默认 approve 所有需要审核的工具调用
        interruption.toolFeedbacks().forEach(toolFeedback -> {
            builder.addToolFeedback(
                    InterruptionMetadata.ToolFeedback.builder(toolFeedback)
                            .result(InterruptionMetadata.ToolFeedback.FeedbackResult.APPROVED)
                            .build()
            );
        });
        return builder.build();
    }
}