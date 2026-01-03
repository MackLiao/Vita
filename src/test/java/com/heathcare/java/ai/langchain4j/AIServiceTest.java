package com.heathcare.java.ai.langchain4j;

import com.healthcare.java.ai.langchain4j.VitaApp;
import com.healthcare.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = VitaApp.class)
public class AIServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

//    @Test
//    public void testChat() {
//        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
//        String answer = assistant.chat("Who are you");
//        System.out.println(answer);
//    }

    @Autowired
    private Assistant assistant;

    @Test
    public void  testAssistant() {
        String answer = assistant.chat("Who are you");
        System.out.println(answer);
    }
}
