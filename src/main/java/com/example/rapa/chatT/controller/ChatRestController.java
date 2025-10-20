package com.example.rapa.chatT.controller;

import com.example.rapa.chatT.model.ChatMessage;
import com.example.rapa.chatT.repo.ChatMessageRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/chat"})
public class ChatRestController {
    private final ChatMessageRepository chatMessageRepository;

    public ChatRestController(ChatMessageRepository repo) {
        this.chatMessageRepository = repo;
    }

    @GetMapping({"/history"})
    public List<ChatMessage> history() {
        return this.chatMessageRepository.findAllByOrderByTimestampAsc();
    }
}
