package com.project.socket.chatapplication.model;

import java.util.ArrayList;
import java.util.List;

public class ActiveUsersStorage {
    public static List<User> activeUsers = new ArrayList<>();
    public static void removeUser(String id){
        for(int i = 0; i < activeUsers.size(); i++){
            if(activeUsers.get(i).equals(id)){
                activeUsers.remove(i);
                break;
            }
        }
    }
}
