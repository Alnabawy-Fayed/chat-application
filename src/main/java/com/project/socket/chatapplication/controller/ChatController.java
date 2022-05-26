package com.project.socket.chatapplication.controller;

import com.project.socket.chatapplication.model.ActiveUsersStorage;
import com.project.socket.chatapplication.model.ChatMessage;
import com.project.socket.chatapplication.model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.logIn")
    @SendTo("/topic/all")
    public ChatMessage logIn(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        //header accessor adds a user info
        headerAccessor.getSessionAttributes().put("userName",chatMessage.getSender());
        ActiveUsersStorage.activeUsers.add(new User(chatMessage.getSender(),headerAccessor.getSessionId()));
        return chatMessage;
    }

    @MessageMapping("chat.send")
    @SendTo("/topic/all")
    public ChatMessage send(@Payload ChatMessage chatMessage){
        return chatMessage;
    }
}
