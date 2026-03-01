package com.spring.ai_tutorial.mcp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class McpTest {

    @Autowired
    private ChatClient chatClient;


    @Test
    void testMcpFileSystem() {
        // 第一步：让 AI 通过 MCP 写文件
        String writeResult = chatClient.prompt()
                .user("请在 /tmp 目录下创建一个名为 hello-mcp.txt 的文件，内容写 'Hello from Spring AI MCP!'")
                .call()
                .content();
        System.out.println("写文件结果：" + writeResult);

        // 第二步：让 AI 读回来验证
        String readResult = chatClient.prompt()
                .user("请读取 /tmp/hello-mcp.txt 文件的内容")
                .call()
                .content();
        System.out.println("读文件结果：" + readResult);

        Assertions.assertThat(readResult).containsIgnoringCase("Hello");
    }
    @Test
    void testListFiles() {
        // 列出目录下的文件
        String result = chatClient.prompt()
                .user("列出 /tmp 目录下的所有文件")
                .call()
                .content();
        System.out.println("目录内容：" + result);
        Assertions.assertThat(result).isNotBlank();
    }
}