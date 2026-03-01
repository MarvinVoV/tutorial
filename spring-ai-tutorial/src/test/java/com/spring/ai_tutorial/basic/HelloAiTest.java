package com.spring.ai_tutorial.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 1. LLM创建
 */
@SpringBootTest
class HelloAiTest {
    @Autowired
    private ChatClient chatClient;

    @Test
    void testHello() {
        String response = chatClient.prompt().user("你好，用一句话介绍你自己").call().content();

        System.out.println("AI 回复：" + response);
        Assertions.assertThat(response).isNotBlank();
    }
}