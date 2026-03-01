package com.spring.ai_tutorial.memory;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. 手动管理会话
 */
@SpringBootTest
public class MultiTurnTest {
    @Autowired
    private ChatModel chatModel;

    @Test
    void testMultiTurn() {
        // 用一个 List 维护对话历史
        List<Message> history = new ArrayList<>();

        // 加入系统提示
        history.add(new SystemMessage("你是一个 Java 技术专家，回答要简洁"));

        // 第一轮
        history.add(new UserMessage("什么是依赖注入？"));
        AssistantMessage reply1 = chatModel.call(new Prompt(history))
                .getResult().getOutput();
        history.add(reply1);  // 把 AI 回复也加入历史
        System.out.println("第一轮：" + reply1.getText());

        // 第二轮，AI 能看到上文
        history.add(new UserMessage("能用代码举个例子吗？"));
        AssistantMessage reply2 = chatModel.call(new Prompt(history))
                .getResult().getOutput();
        history.add(reply2);
        System.out.println("第二轮：" + reply2.getText());

        // 第三轮，问一个依赖上文的问题
        history.add(new UserMessage("上面的例子里，@Autowired 是必须的吗？"));
        AssistantMessage reply3 = chatModel.call(new Prompt(history))
                .getResult().getOutput();
        System.out.println("第三轮：" + reply3.getText());
    }
}
