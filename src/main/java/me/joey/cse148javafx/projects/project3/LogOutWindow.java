package me.joey.cse148javafx.projects.project3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogOutWindow extends Application {

    @Override
    public void start(Stage stage){


        Text confirm = new Text("Do you really want to logout");
        Button yes = new Button("Yes, Logout");
        Button no = new Button("No");


        HBox buttons = new HBox(yes, no);
        BorderPane root = new BorderPane();

        buttons.setSpacing(5);

        root.setTop(confirm);
        root.setBottom(buttons);

        buttons.setAlignment(Pos.BOTTOM_CENTER);

        Scene s = new Scene(root);

        stage.setScene(s);
        stage.show();
    }


}
