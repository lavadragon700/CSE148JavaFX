package me.joey.cse148javafx.projects.project3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Library implements Serializable {

    private static File savePath = new File("C:\\CSE148JavaFX\\src\\main\\resources\\library.dat");
    private static Map<User, Library> data = new HashMap<>();
    //Use in final project
    // private static File savePath = new File(System.getProperty("user.dir"));


    private File libraryFile;
    private Book[] books;
    private User user;

    public Library(User user){
        this(user, 16);
    }

    public Library(User user, int size){
        this.user = user;
        books = new Book[size];
        libraryFile = new File(savePath.getPath());
        if(!libraryFile.exists()){
            try {libraryFile.createNewFile();}
            catch (IOException e) {e.printStackTrace();}
        }
    }

    public static File getSavePath(){
        return savePath;
    }

    public void addBook(Book...book){
        books = generateArray(books, book.length);
        for(int i = 0; i < book.length; i++){
            for(int j = 0; j < books.length; j++){
                if(books[j] == null){
                    books[j] = book[i];
                    break;
                }
            }
        }
    }

    public Book[] getBooks() {
        return books;
    }

    public void removeBook(Book book){
        if(!contains(book)) return;

        for(int i = 0; i < books.length; i++){
            if(books[i] == book){
                books[i] = null;
                try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(savePath))){
                    data.put(user, this);
                    objOut.writeObject(data);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean contains(Book book){
        for (Book b : books) {
            if (b == book) {
                return true;
            }
        }
        return false;
    }

    public static Library findLibrary(User user){
        final Library[] lib = {null};
        data.forEach((key, value) ->{
            if(user.equals(key)){
                lib[0] = value;
            }
        });
        return lib[0];
    }

    public static Map<User, Library> getData(){
        return data;
    }

    public Book findBookByTitle(String title){
        for(Book b: books){
            if(b == null) continue;

            if(b.getTitle().equals(title)){
                return b;
            }
        }
        return null;
    }

    private Book[] generateArray(Book[] books, int indexesNeeded){
        Book[] arr = new Book[books.length + indexesNeeded];
        System.out.println(arr.length);
        for(int i = 0; i < arr.length - 1; i++){
            arr[i] = books[i];
        }
        return arr;
    }
}
