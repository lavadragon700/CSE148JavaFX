package me.joey.cse148javafx.projects.project3;

public class StrArrayList implements WordList{
    private String[] words;
    private int length;

    public StrArrayList(){
        length = 1;
        words = new String[length];
    }

    @Override
    public void addWord(String word) {
        String[] arr = new String[words.length + 1];
        for(int i = 0; i < words.length; i++){
            arr[i] = words[i];
            if(words[i] == null){
                words[i] = word;
                return;
            }
        }

        arr[arr.length - 1] = word;
        words = arr;
    }

    @Override
    public void addWord(String word, int index) {
        if(getLength() - 1 < index){
            String[] arr = new String[index + 1];
            for(int i = 0; i < words.length - 1; i++){
                arr[i] = words[i];
            }
            words = arr;
        }

        words[index] = word;
    }

    @Override
    public void removeWord(String word) {
        for(int i = 0; i < getLength() - 1; i++){
            if(words[i].equals(word)){
                words[i] = null;
            }
        }
    }

    @Override
    public void removeWord(int index) {
        if(getLength() - 1 < index){
            return;
        }

        words[index] = null;
    }

    @Override
    public void removeAllWords() {
        words = new String[getLength()];
    }

    @Override
    public int getIndexOfWord(String word) {
        for(int i = 0; i < getLength(); i++){
            if(words[i].equals(word)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String getWord(int index) {
        if(getLength() - 1 < index){
            return null;
        }

        return words[index];
    }

    @Override
    public void setWordIndex(String word, int index) {
        if(getLength() - 1 < index){
            return;
        }

        words[index] = word;
    }

    @Override
    public int getLength() {
        return words.length;
    }

    @Override
    public boolean isEmpty() {
        for(String i: words){
            if(i != null){
                return false;
            }
        }
        return true;
    }
}
