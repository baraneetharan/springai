package com.leads.springai.controller;

import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/chat")
public class ChatController {
    private final ChatClient chatClient;


    @Autowired
    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    
    @SuppressWarnings("rawtypes")
    @GetMapping("/chat")
    public Map generate(@RequestParam(value = "message", defaultValue = "TOP 5 AI initiative in india") String message) {
        return Map.of("generation", chatClient.call(message));
    }
}
