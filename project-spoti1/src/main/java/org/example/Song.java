package org.example;

public class Song {
    String title;
    String author;
    boolean explicit;
    int duration;

    public Song(String title, String author, int duration, boolean explicit) {
        this.title = title;
        this.author = author;
        this.duration = duration;
        this.explicit = explicit;
    }
}
