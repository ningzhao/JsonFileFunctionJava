package com.ningzhao;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    final private String id;
    final private String user_id;
    public List<String> song_ids;

    public Playlist(String id, String user_id)
    {
        this.id = id;
        this.user_id = user_id;
        this.song_ids = new ArrayList<>();
    }

    public Playlist(String id, String user_id, List<String> song_ids)
    {
        this.id = id;
        this.user_id = user_id;
        this.song_ids = song_ids;
    }

    public String getId()
    {
        return this.id;
    }

    public String getUserId()
    {
        return this.user_id;
    }

    public List<String> getSongIds()
    {
        return this.song_ids;
    }

    public void addSongId(String song_id)
    {
        this.song_ids.add(song_id);
    }

    public void addSongIds(List<String> song_ids)
    {
        this.song_ids.addAll(song_ids);
    }
}
