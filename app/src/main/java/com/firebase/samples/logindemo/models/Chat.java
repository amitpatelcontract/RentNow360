package com.firebase.samples.logindemo.models;

/**
 * @author greg
 * @since 6/21/13
 */

public class Chat {

    private String message;
    private String author;
    private String age;
    private String gender;
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() {
    }

    public Chat(String message, String author) {
        this.message = message;
        this.author = author;
    }
    Chat(String message, String author, String age, String gender) {
        this.message = message;
        this.author = author;
        this.age = age;
        this.gender = gender;
    }
    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
