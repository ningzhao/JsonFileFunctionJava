package com.ningzhao;

public interface IMixtapeClient {
    void ingestMixtape(MixtapeDocument mixtapeDocument);

    void addPlaylist(Playlist playlist);

    void removePlaylist(String playlist_id);

    void addSongToPlaylist(String playlist_id, String song_id);

    String exportJsonString();
}
