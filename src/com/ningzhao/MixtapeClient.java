package com.ningzhao;

import com.google.gson.Gson;

public class MixtapeClient implements IMixtapeClient {

    private MixtapeDocument mixtapeDocument;

    @Override
    public void ingestMixtape(MixtapeDocument mixtapeDocument)
    {
        this.mixtapeDocument = mixtapeDocument;
    }

    @Override
    public void addPlaylist(Playlist playlist)
    {
        this.mixtapeDocument.playlists.add(playlist);
    }

    @Override
    public void removePlaylist(String playlist_id)
    {
        if (!this.mixtapeDocument.playlists.removeIf(playlist -> playlist.getId().equals((playlist_id))))
        {
            new Exception(String.format("cannot find the playlist for the %s", playlist_id)).printStackTrace();
        }
    }

    @Override
    public void addSongToPlaylist(String playlist_id, String song_id)
    {
        int index = 0;

        for (Playlist playlist : this.mixtapeDocument.playlists)
        {
            if (playlist.getId().equals(playlist_id)) break;
            else index++;
        }

        if (index >= this.mixtapeDocument.playlists.size())
        {
            new Exception(String.format("cannot find the playlist for the %s", playlist_id)).printStackTrace();
            return;
        }

        this.mixtapeDocument.playlists.get(index).song_ids.add(song_id);
    }

    @Override
    public String exportJsonString()
    {
        return new Gson().toJson(this.mixtapeDocument, MixtapeDocument.class);
    }
}
