package com.spring.ai_tutorial.stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 1. stream basic example
 */
@SpringBootTest
public class StreamingTest {
    @Autowired
    private ChatClient chatClient;

    @Test
    void testStreaming() throws InterruptedException {
        // call() 换成 stream()，content() 换成 content() 返回 Flux<String>
        Flux<String> flux = chatClient.prompt()
                .user("用200字介绍一下 Spring Boot")
                .stream()
                .content();

        // 订阅，每收到一个字就打印，不换行,注意:这里是异步的
        flux.subscribe(
                chunk -> System.out.print(chunk),           // 收到数据
                error -> System.err.println("出错：" + error), // 出错
                () -> System.out.println("\n===完成===")      // 完成
        );

        // 测试里需要等待异步流完成，否则测试直接结束
        Thread.sleep(30000);
    }


    @Test
    void testStreamingBlocking() {
        List<String> chunks = chatClient.prompt()
                .user("用200字介绍一下 Spring Boot")
                .stream()
                .content()
                .collectList()   // 收集所有 chunk 成 List，阻塞直到完成
                .block();

        String fullResponse = String.join("", chunks);
        System.out.println("完整回复：" + fullResponse);
        Assertions.assertThat(fullResponse).isNotBlank();
    }

    @Test
    void testStreamingWithMemory() {
        String conversationId = "stream-session-1";

        // 第一轮
        System.out.print("第一轮：");
        chatClient.prompt()
                .user("我在学习 Spring AI，目前学到了流式输出")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()
                .content()
                .doOnNext(chunk -> System.out.print(chunk))
                .doOnComplete(() -> System.out.println()) // print a new line
                .collectList()
                .block();

        // 第二轮，AI 应该记得上文
        System.out.print("第二轮：");
        chatClient.prompt()
                .user("我目前学到哪里了？下一步建议学什么？")
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()
                .content()
                .doOnNext(chunk -> System.out.print(chunk))
                .doOnComplete(() -> System.out.println())
                .collectList()
                .block();

    }
}
