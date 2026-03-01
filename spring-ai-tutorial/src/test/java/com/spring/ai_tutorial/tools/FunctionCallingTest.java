package com.spring.ai_tutorial.tools;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FunctionCallingTest {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private WeatherTools weatherTools;
    @Autowired
    private OrderTools orderTools;

    @Test
    void testFunctionCalling() {
        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultSystem("你是一个生活助手，回答用户问题时请调用工具获取准确信息")
                .defaultTools(weatherTools)  // 注册工具
                .build();

        String response = chatClient.prompt()
                .user("北京和上海今天天气怎么样？顺便告诉我现在几点")
                .call()
                .content();

        System.out.println("AI 回复：" + response);
        Assertions.assertThat(response).isNotBlank();
    }

    @Test
    void testFunctionCalling1() {
        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultSystem("你是一个生活助手，回答用户问题时请调用工具获取准确信息")
                .defaultTools(weatherTools, orderTools)  // 注册工具
                .build();

        String response = chatClient.prompt()
                .user("北京和上海今天天气怎么样？顺便告诉我现在几点； 哦 另外我的订单ORD-12345发货了吗？")
                .call()
                .content();

        System.out.println("AI 回复：" + response);
        Assertions.assertThat(response).isNotBlank();
    }
}