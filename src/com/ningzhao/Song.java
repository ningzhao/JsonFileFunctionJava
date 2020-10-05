package com.ningzhao;

public class Song {
    final private String id;
    final private String artist;
    final private String title;

    public Song(String id, String artist, String title)
    {
        this.id = id;
        this.artist = artist;
        this.title = title;
    }

    public String getId()
    {
        return this.id;
    }

    public String getArtist()
    {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }
}
