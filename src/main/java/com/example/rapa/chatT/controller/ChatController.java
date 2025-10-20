package com.example.rapa.chatT.controller;


import com.example.rapa.chatT.model.ChatMessage;
import com.example.rapa.chatT.repo.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/room")
    public ChatMessage sendMessage(ChatMessage message){
        message.setTimestamp(LocalDateTime.now());
        return chatMessageRepository.save(message);
    }

    @GetMapping("/chat/history")
    @ResponseBody
    public List<ChatMessage> history(){
        return chatMessageRepository.findAll();
    }


    @GetMapping("/chatroom")
    public String chatroom(Model model, Authentication authentication) {
        List<ChatMessage> messages = chatMessageRepository.findAllByOrderByTimestampAsc();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("messages", messages);
        return "chatroom";
    }
}
