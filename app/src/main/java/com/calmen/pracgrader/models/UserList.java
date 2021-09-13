package com.calmen.pracgrader.models;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public boolean isExist(String username) {
        if (isEmpty()) {
            return false;
        } else {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public User getUserByUsername(String username) {
        if (!isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }
}
