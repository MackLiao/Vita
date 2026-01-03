package com.healthcare.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {

    // @SystemMessage("You are a good friend mine, respond with your southern accent. Today is {{current_date}}")
    @SystemMessage(fromResource = "prompt-template.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @UserMessage("You are a good friend mine, respond with your southern accent. {{message}}")
    String chat2(@MemoryId int memoryId, @V("message") String userMessage);

    @SystemMessage(fromResource = "prompt-template2.txt")
    String chat3(@MemoryId int memoryId,
                 @UserMessage String userMessage,
                 @V("username") String username,
                 @V("age") int age
    );


}
