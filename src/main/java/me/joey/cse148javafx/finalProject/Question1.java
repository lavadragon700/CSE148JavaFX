package me.joey.cse148javafx.finalProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Question1 extends Application {
    TextArea stats = new TextArea();
    String[] data = new String[4];
    int answerNum = 0;
    int score = 0;
    int wrong = 0;
    int skipped = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {




        TextField question = new TextField();
        Text equals = new Text("=");
        TextField answer = new TextField();
        Button newBtn = new Button("New");
        Button answerBtn = new Button("Answer");
        Button skipBtn = new Button("Skip");

        try(ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("C:\\CSE148JavaFX\\src\\main\\resources\\stats.dat"))){
            data = (String[]) objIn.readObject();
            if(data[0] != null) stats.setText(data[0]);
            if(data[1] != null) score = Integer.parseInt(data[1]);
            if(data[2] != null) wrong = Integer.parseInt(data[2]);
            if(data[3] != null) skipped = Integer.parseInt(data[3]);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        HBox middle = new HBox(question, equals, answer);
        HBox textArea = new HBox(stats);
        HBox newBtns = new HBox(newBtn);
        HBox rightBtns = new HBox(answerBtn, skipBtn);
        HBox buttons = new HBox(newBtns, rightBtns);

        middle.setSpacing(10);
        rightBtns.setSpacing(10);

        stats.setEditable(false);
        question.setEditable(false);

        textArea.setPadding(new Insets(20, 20, 10, 20));
        middle.setPadding(new Insets(0, 0, 0, 20));
        buttons.setPadding(new Insets(0, 0, 20, 20));
        newBtns.setPadding(new Insets(10, 50, 10, 0));
        rightBtns.setPadding(new Insets(10, 0, 10, 0));


        answer.setMinWidth(50);
        question.setMinWidth(150);
        newBtn.setMaxWidth(75);
        newBtn.setMinWidth(75);
        answerBtn.setMaxWidth(75);
        answerBtn.setMinWidth(75);
        skipBtn.setMaxWidth(75);
        skipBtn.setMinWidth(75);

        newBtn.setOnAction(e ->{
            newQuestion(question);
        });

        answerBtn.setOnAction(e ->{
            if(answer.getText().equals("")){
                wrong++;
                setText();
                return;
            }

            int a = Integer.parseInt(answer.getText());



            if(a == answerNum){
                score++;
                setText();
                newQuestion(question);
            }else{
                wrong++;
                setText();
                newQuestion(question);
            }

        });

        skipBtn.setOnAction(e ->{
            skipped++;
            setText();
            newQuestion(question);
        });


        newQuestion(question);


        GridPane root = new GridPane();
        root.addRow(0, textArea);
        root.addRow(1, middle);
        root.addRow(2, buttons);



        Scene s = new Scene(root);

        stage.setScene(s);
        stage.show();
    }

    public void setText(){
        stats.setText("question answered correctly: " + score + "\nquestion answered wrong: " + wrong + "\nquestion skipped: " + skipped );
        data[0] = stats.getText();
        data[1] = String.valueOf(score);
        data[2] = String.valueOf(wrong);
        data[3] = String.valueOf(skipped);
        try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("C:\\CSE148JavaFX\\src\\main\\resources\\stats.dat"))){
            objOut.writeObject(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void newQuestion(TextField question){
        Random ran = new Random();
        int rand1 = ran.nextInt(0, 99);
        int rand2 = ran.nextInt(0, 99);
        question.setText(rand1 + " + " + rand2);
        answerNum = rand1 + rand2;
    }



}
