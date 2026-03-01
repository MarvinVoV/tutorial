package com.spring.ai_tutorial.memory;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 3. 管理会话记忆
 */
@SpringBootTest
class ChatMemoryTest {

    @Autowired
    private ChatClient chatClient;

    @Test
    void testChatMemory() {
        String conversationId = "test-session-1";

        String reply1 = chatClient.prompt()
                .user("我叫小明，我是一名 Java 开发者")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
        System.out.println("第一轮：" + reply1);

        String reply2 = chatClient.prompt()
                .user("我叫什么名字？我是做什么的？")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
        System.out.println("第二轮：" + reply2);
    }
}