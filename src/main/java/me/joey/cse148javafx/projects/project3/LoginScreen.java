package me.joey.cse148javafx.projects.project3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class LoginScreen extends Application {
    @Override
    public void start(Stage stage){

        DataCenter data = DataCenter.getInstance();

        Label usernameTxt = new Label("Username: ");
        TextField username = new TextField();
        Label passwordTxt = new Label("Password: ");
        PasswordField password = new PasswordField();
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");

        username.setMaxWidth(150);
        password.setMaxWidth(150);

        loginButton.setOnAction(e ->{
            String user = username.getText();
            String pass = password.getText();
            Alert alert;
            if (DataCenter.getInstance().findUser(user, pass)) {
                User.setLoggedInUser(new User(user, pass));
                MainWindow mainWindow = new MainWindow();
                mainWindow.start(new Stage());
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("User Login");
                alert.setContentText("Login successfully");
                stage.close();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Login Failed");
                alert.setContentText("Cannot find the user. If you do not have an account please signup");
            }
            alert.showAndWait();
        });

        signUpButton.setOnAction(e->{
            SignUpScreen signUpScreen = new SignUpScreen();

            try {
                signUpScreen.start(new Stage());
                stage.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox buttons = new HBox(loginButton, signUpButton);
        buttons.setSpacing(47.5);
        GridPane root = new GridPane();

        root.addRow(0, usernameTxt, username);
        root.addRow(1, passwordTxt, password);
        root.add(buttons, 1, 2);
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);

        Scene s = new Scene(root);

        try {
            if (!User.getSavePath().exists())    User.getSavePath().createNewFile();
            if (!Library.getSavePath().exists()) Library.getSavePath().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(User.getSavePath()))){
            for(User u: (ArrayList<User>) objIn.readObject()){
                data.addUser(u);
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(Library.getSavePath()))){
            Library.getData().putAll((Map<User, Library>) objIn.readObject());
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }



        stage.setScene(s);
        stage.show();
    }



}
