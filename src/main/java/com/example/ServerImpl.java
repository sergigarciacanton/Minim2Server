package com.example;

import com.example.models.Badge;
import com.example.models.User;

import java.util.List;

public class ServerImpl implements Server {
    static Server instance;
    List<User> usersList;
    List<Badge> badgesList;

    public static Server getInstance(){
        if (instance==null) instance = new ServerImpl();
        return instance;
    }

    @Override
    public User getUser(int id) {
        boolean found = false;
        int i = 0;
        while(i < usersList.size() && !found) {
            if(id == usersList.get(i).getId()) found = true;
            else i++;
        }
        if(found) return usersList.get(i);
        else return null;
    }

    @Override
    public List<Badge> getBadges() {
        return badgesList;
    }

    @Override
    public void addUser(User user) {
        usersList.add(user);
    }

    @Override
    public void addBadge(Badge badge) {
        badgesList.add(badge);
    }
}
