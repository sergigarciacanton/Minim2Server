package com.example;

import com.example.models.Badge;
import com.example.models.User;

import java.util.List;

public interface Server {
    User getUser(int id);
    List<Badge> getBadges();
    void addUser(User user);
    void addBadge(Badge badge);
}
