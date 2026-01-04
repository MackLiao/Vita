package com.healthcare.java.ai.langchain4j.controller;

import com.healthcare.java.ai.langchain4j.assistant.VitaAgent;
import com.healthcare.java.ai.langchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "Vita")
@RestController
@RequestMapping("/vita")
public class VitaController {

    @Autowired
    private VitaAgent vitaAgent;

    @Operation(summary = "conversation")
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatForm chatForm) {
        return vitaAgent.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
