package me.joey.cse148javafx.projects.project3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Serializable {
    private String title;
    private String genre;
    private String author;
    private String isbn;
    private String language;
    private List<String> text;

    public Book(String title, String author, String genre, String isbn, String language){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.language = language;
        this.text = new ArrayList<>();
    }

    public void addText(String...txt){
        text.addAll(Arrays.stream(txt).toList());
    }

    public void addText(List<String> txt){
        text.addAll(txt);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getLanguage() {
        return language;
    }


}
