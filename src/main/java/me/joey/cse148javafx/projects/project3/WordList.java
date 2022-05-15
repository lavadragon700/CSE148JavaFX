package me.joey.cse148javafx.projects.project3;

public interface WordList {
    void addWord(String word);
    void addWord(String word, int index);
    void removeWord(String word);
    void removeWord(int index);
    void removeAllWords();
    int getIndexOfWord(String word);
    String getWord(int index);
    void setWordIndex(String word, int index);
    int getLength();
    boolean isEmpty();
}
