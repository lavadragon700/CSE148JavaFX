package me.joey.cse148javafx.projects.project3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainWindow extends Application {
    private static Stage mainStage;

    Library userLib;
    ListView<String> books = new ListView<>();

    @Override
    public void start(Stage stage){
        mainStage = stage;
        userLib = Library.findLibrary(User.getLoggedInUser());
        TextField searchBar = new TextField();
        ComboBox<String> searchType = new ComboBox<>();
        Label title = new Label("Title: ");
        Label author = new Label("Author: ");
        Label genre = new Label("Genre: ");
        Label language = new Label("Language: ");
        Label isbn = new Label("ISBN: ");
        TextField titleTxt = new TextField();
        TextField authorTxt = new TextField();
        TextField genreTxt = new TextField();
        TextField languageTxt = new TextField();
        TextField isbnTxt = new TextField();
        Button logOut = new Button("Log Out");
        Button search = new Button("Search");
        Button addBook = new Button("Add a Book");
        Button removeBook = new Button("Remove a Book");

        List<String> searchTypeNames = new ArrayList<>();

        searchTypeNames.add("Author");
        searchTypeNames.add("Genre");
        searchTypeNames.add("Title");


        searchType.getItems().addAll(searchTypeNames);
        searchType.setItems(searchType.getItems());
        searchType.getSelectionModel().selectFirst();

        searchBar.setPromptText("Search");
        titleTxt.setPromptText("Title");
        authorTxt.setPromptText("Author");
        genreTxt.setPromptText("Genre");
        languageTxt.setPromptText("Language");
        isbnTxt.setPromptText("ISBN");

        titleTxt.setEditable(false);
        authorTxt.setEditable(false);
        genreTxt.setEditable(false);
        languageTxt.setEditable(false);
        isbnTxt.setEditable(false);
        if(userLib == null){
            userLib = new Library(User.getLoggedInUser());
        }

        for(Book b: userLib.getBooks()){
            if(b != null){
                books.getItems().add(b.getTitle());
            }
        }

        books.setItems(books.getItems());

        searchBar.setMaxWidth(Double.MAX_VALUE);
        addBook.setMaxWidth(250);
        removeBook.setMaxWidth(250);

        HBox logOutBox = new HBox(logOut);
        HBox top = new HBox(searchBar, searchType, search);
        HBox bottom = new HBox(addBook, removeBook);
        VBox bookDisplay = new VBox(title, titleTxt, author, authorTxt, genre, genreTxt, language, languageTxt, isbn, isbnTxt);
        GridPane root = new GridPane();

        addBook.setOnAction(e -> addBookWindow());
        removeBook.setOnAction(e -> {
            removeBook(books);
            titleTxt.setText("");
            authorTxt.setText("");
            genreTxt.setText("");
            languageTxt.setText("");
            isbnTxt.setText("");
        });

        logOut.setOnAction(e ->{
            LogOutWindow logOutWindow = new LogOutWindow();
            logOutWindow.start(new Stage());
        });

        search.setOnAction(e ->{
            String text = searchBar.getText();

            if(text.equals("")){
                refreshList();
            }

            ArrayList<String> list = new ArrayList<>();
            for(Book b: userLib.getBooks()){
                if(b == null) continue;

                switch(searchType.getSelectionModel().getSelectedItem()){
                    case "Author":
                        if(b.getAuthor().toLowerCase().contains(text.toLowerCase())){
                            System.out.println(b.getAuthor().contains(text));
                            list.add(b.getTitle());
                        }
                        break;
                    case "Genre":
                        if(b.getGenre().toLowerCase().contains(text.toLowerCase())){
                            list.add(b.getTitle());
                        }
                        break;
                    case "Title":
                        if(b.getTitle().toLowerCase().contains(text.toLowerCase())){
                            list.add(b.getTitle());
                        }
                        break;
                }
            }
            books.getItems().remove(0, books.getItems().size());
            books.getItems().addAll(list);
            books.setItems(books.getItems());
        });



        books.setOnMouseClicked(e ->{
            if(books.getSelectionModel().getSelectedItem() == null){
                titleTxt.setText("");
                authorTxt.setText("");
                genreTxt.setText("");
                languageTxt.setText("");
                isbnTxt.setText("");
                return;
            }
            Book book = userLib.findBookByTitle(books.getSelectionModel().getSelectedItem());
            titleTxt.setText(book.getTitle());
            authorTxt.setText(book.getAuthor());
            genreTxt.setText(book.getGenre());
            languageTxt.setText(book.getLanguage());
            isbnTxt.setText(book.getIsbn());
        });

        searchBar.setMinWidth(300);

        bottom.setSpacing(5);
        top.setSpacing(5);
        root.setHgap(5);
        root.setVgap(5);

        logOutBox.setAlignment(Pos.TOP_RIGHT);

        root.addRow(0, top);
        root.add(logOutBox, 1, 0);
        root.addRow(1, books, bookDisplay);
        root.add(bottom, 0, 2);

        root.setPadding(new Insets(10));

        Scene s = new Scene(root);

        stage.setScene(s);
        stage.show();
    }

    public void removeBook(ListView<String> books){
        userLib.removeBook(userLib.findBookByTitle(books.getSelectionModel().getSelectedItem()));
        books.getItems().remove(books.getSelectionModel().getSelectedIndex());
    }

    public void addBookWindow(){
        Stage stage = new Stage();

        Label title = new Label("Title: ");
        Label genre = new Label("Genre: ");
        Label author = new Label("Author: ");
        Label language = new Label("Language: ");
        Label isbn = new Label("ISBN: ");
        TextField titleTxt = new TextField();
        TextField genreTxt = new TextField();
        TextField authorTxt = new TextField();
        TextField languageTxt = new TextField();
        TextField isbnTxt = new TextField();
        Button addBook = new Button("Add Book");
        Button cancel = new Button("Cancel");


        GridPane root = new GridPane();

        root.setPadding(new Insets(10));

        root.addRow(0, title, titleTxt);
        root.addRow(1, genre, genreTxt);
        root.addRow(2, author, authorTxt);
        root.addRow(3, language, languageTxt);
        root.addRow(4, isbn, isbnTxt);
        root.addRow(5, addBook, cancel);

        addBook.setOnAction( e ->{
            userLib.addBook(new Book(titleTxt.getText(), authorTxt.getText(), genreTxt.getText(), isbnTxt.getText(), languageTxt.getText()));
            refreshList();
            stage.close();

        });

        cancel.setOnAction(e ->{
            stage.close();
        });

        Scene s = new Scene(root);

        stage.setScene(s);
        stage.show();
    }

    public void refreshList(){
        for(Book b: userLib.getBooks()){
            if(b != null){
                if(!books.getItems().contains(b.getTitle())) books.getItems().add(b.getTitle());
            }
        }

        books.setItems(books.getItems());
    }

    public static void close(){
        if(mainStage != null){
            mainStage.close();
        }
    }
}
