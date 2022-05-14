package me.joey.cse148javafx.projects.project3;

import java.io.*;
import java.util.ArrayList;


public class DataCenter {

    private static DataCenter instance = null;
    private ArrayList<User> userList;

    private DataCenter() {
        userList = new ArrayList<>();
    }

    public static DataCenter getInstance() {

        if (instance == null) {
            instance = new DataCenter();
        }

        return instance;
    }

    public void addUser(User u)  {
        if(userList.contains(u)){
            return;
        }

        userList.add(u);

        try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(User.getSavePath()))){
            objOut.writeObject(userList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String username){
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public boolean findUser(String username, String password) {
        for (User user : userList) {
            if (user.equals(new User(username, password))) {
                return true;
            }
        }
        return false;
    }
}
