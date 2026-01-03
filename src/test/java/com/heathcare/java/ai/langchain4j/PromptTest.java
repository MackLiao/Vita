package com.heathcare.java.ai.langchain4j;

import com.healthcare.java.ai.langchain4j.VitaApp;
import com.healthcare.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = VitaApp.class)
public class PromptTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testSystemMessage() {
        String response = separateChatAssistant.chat(4, "What the date is?");
        System.out.println(response);
    }

    @Test
    public void testUserInfo() {
        String username = "Mack";
        int age = 23;

        String response = separateChatAssistant.chat3(5, "who am I? how old am I?", username, age);
        System.out.println(response);

    }
}
