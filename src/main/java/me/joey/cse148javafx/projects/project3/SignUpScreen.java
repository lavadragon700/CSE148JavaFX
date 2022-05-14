package me.joey.cse148javafx.projects.project3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignUpScreen extends Application {
    @Override
    public void start(Stage stage){
        Label usernameTxt = new Label("Username: ");
        TextField username = new TextField();
        Label passwordTxt = new Label("Password: ");
        PasswordField password = new PasswordField();
        Label passwordConfirmTxt = new Label("Confirm Password: ");
        PasswordField confirmPassword = new PasswordField();
        Button signUp = new Button("Sign Up");

        signUp.setOnAction(e->{
            String user = username.getText();
            String pass = password.getText();
            Alert alert;
            if(pass.equals(confirmPassword.getText())){
                if(DataCenter.getInstance().findUser(user, pass) || DataCenter.getInstance().usernameExists(user)){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("That user already exists");
                } else{
                    User u = new User(user, pass);
                    LoginScreen loginScreen = new LoginScreen();
                    DataCenter.getInstance().addUser(u);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Signup Successful");
                    alert.setContentText("You have created an account successfully");
                    loginScreen.start(new Stage());
                    stage.close();
                }
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Incorrect Password");
            }
            alert.show();
        });

        GridPane root = new GridPane();

        root.setAlignment(Pos.CENTER);
        root.addRow(0, usernameTxt, username);
        root.addRow(1, passwordTxt, password);
        root.addRow(2, passwordConfirmTxt, confirmPassword);
        root.add(signUp, 1, 3);
        root.setVgap(10);

        Scene s = new Scene(root);

        stage.setScene(s);
        stage.show();
    }
}
