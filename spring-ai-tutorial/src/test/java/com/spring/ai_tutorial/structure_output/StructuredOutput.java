package com.spring.ai_tutorial.structure_output;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class StructuredOutput {
    @Autowired
    private ChatClient chatClient;

    // 定义你想要的数据结构
    record MovieReview(
            String title,
            String director,
            int year,
            double rating,
            String summary
    ) {}

    /**
     * 直接返回 Java 对象（最常用）
     */
    @Test
    void testStructuredOutput() {
        MovieReview review = chatClient.prompt()
                .user("介绍一下电影《肖申克的救赎》，包括导演、年份、评分和简介")
                .call()
                .entity(MovieReview.class);  // 直接返回 Java 对象

        System.out.println("片名：" + review.title());
        System.out.println("导演：" + review.director());
        System.out.println("年份：" + review.year());
        System.out.println("评分：" + review.rating());
        System.out.println("简介：" + review.summary());

        Assertions.assertThat(review.title()).isNotBlank();
        Assertions.assertThat(review.year()).isGreaterThan(1990);
    }

    record Book(String title, String author, int year) {}

    /**
     * 返回 List
     */
    @Test
    void testStructuredOutputList() {
        List<Book> books = chatClient.prompt()
                .user("列出5本经典的 Java 编程书籍，包括书名、作者和出版年份")
                .call()
                .entity(new ParameterizedTypeReference<List<Book>>() {});

        books.forEach(book ->
                System.out.printf("《%s》 - %s (%d)%n", book.title(), book.author(), book.year())
        );

        Assertions.assertThat(books).hasSize(5);
    }


    /**
     * 返回 Map
     */
    @Test
    void testStructuredOutputMap() {
        Map<String, Object> result = chatClient.prompt()
                .user("分析'Spring AI 很好用'这句话，返回情感(sentiment)、置信度(confidence)、关键词(keywords)")
                .call()
                .entity(new ParameterizedTypeReference<Map<String, Object>>() {});

        System.out.println("情感：" + result.get("sentiment"));
        System.out.println("置信度：" + result.get("confidence"));
        System.out.println("关键词：" + result.get("keywords"));
    }
}
