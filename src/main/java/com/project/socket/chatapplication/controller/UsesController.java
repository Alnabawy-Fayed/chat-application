package com.project.socket.chatapplication.controller;

import com.project.socket.chatapplication.model.ActiveUsersStorage;
import com.project.socket.chatapplication.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:63342")
public class UsesController {
    @GetMapping("/active")
    public List<User> getActiveUsers(){
        return ActiveUsersStorage.activeUsers;
    }
}
