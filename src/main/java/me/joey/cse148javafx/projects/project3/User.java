package me.joey.cse148javafx.projects.project3;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private static final File savePath = new File("D:\\CSE148JavaFX\\src\\main\\resources\\users.dat");
    private static User loggedInUser;

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public static File getSavePath(){
        return savePath;
    }

    public static User getLoggedInUser(){
        return loggedInUser;
    }

    public static void setLoggedInUser(User user){
        loggedInUser = user;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (!(obj instanceof User))
            return false;

        User user = (User)obj;
        return username.equals(user.username) && password.equals(user.password);
    }
}
