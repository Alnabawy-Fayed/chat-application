package com.project.socket.chatapplication.config;

import com.project.socket.chatapplication.model.ActiveUsersStorage;
import com.project.socket.chatapplication.model.ChatMessage;
import com.project.socket.chatapplication.model.ChatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SocketStatus {
    @Autowired(required = true)
    SimpMessagingTemplate messagingTemplate;
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event){

    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("userName");
        if(userName != null){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setChatType(ChatType.LEAVE);
            chatMessage.setSender(userName);
            ActiveUsersStorage.removeUser(headerAccessor.getSessionId());
            messagingTemplate.convertAndSend("/topic/all",chatMessage);
        }
    }

}
