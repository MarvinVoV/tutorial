package com.spring.ai_tutorial.rag;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RagTest {

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private ChatClient chatClient;

    @BeforeEach
    void initKnowledgeBase() {
        // 模拟公司内部文档，实际项目里从文件、数据库读取
        List<Document> documents = List.of(
                new Document("产品A的价格是999元，支持7天无理由退换货，保修期1年"),
                new Document("产品B的价格是1999元，支持30天无理由退换货，保修期2年"),
                new Document("退款流程：登录账号 -> 我的订单 -> 申请退款 -> 等待审核（1-3个工作日）"),
                new Document("客服工作时间：周一到周五 9:00-18:00，节假日不提供服务"),
                new Document("会员等级分为：普通会员、银牌会员、金牌会员，消费满1000元升级银牌")
        );

        // 写入向量数据库，框架自动调用 Embedding 模型转成向量
        vectorStore.add(documents);
        System.out.println("知识库初始化完成，共 " + documents.size() + " 条文档");
    }

    @Test
    void testRag() {
        // 用 QuestionAnswerAdvisor 自动完成：搜索 -> 注入上下文 -> 生成回答
        ChatClient ragClient = chatClient.mutate().
                defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .build();

        String question = "产品B能退货吗？退款要多久？";
        System.out.println("问题：" + question);

        String answer = ragClient.prompt()
                .user(question)
                .call()
                .content();

        System.out.println("回答：" + answer);
        Assertions.assertThat(answer).isNotBlank();
    }


    @Test
    void testRagMultiQuestion() {
        ChatClient ragClient = chatClient.mutate()
                .defaultSystem("你是客服助手，只根据提供的资料回答问题，如果资料里没有相关信息就说不知道")
                .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .build();

        List<String> questions = List.of(
                "产品A多少钱？",
                "客服几点上班？",
                "怎么升级会员？",
                "你们有产品C吗？"   // 知识库里没有，AI 应该说不知道
        );

        for (String q : questions) {
            String answer = ragClient.prompt()
                    .user(q)
                    .call()
                    .content();
            System.out.println("Q: " + q);
            System.out.println("A: " + answer);
            System.out.println("---");
        }
    }
}