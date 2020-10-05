package com.ningzhao;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static String AddPlaylist = "AddPlaylist";
    static String RemovePlaylist = "RemovePlaylist";
    static String AddSong = "AddSong";

    public static void main(String[] args) {
        try
        {
            Gson gson = new Gson();

            File ingestFile = new File("mixtape-data.json");
            Reader fileReader = new FileReader(ingestFile);
            MixtapeDocument mixtapeDocument = gson.fromJson(fileReader, MixtapeDocument.class);

            IMixtapeClient client = new MixtapeClient();
            client.ingestMixtape(mixtapeDocument);

            File changesFile = new File("changes.txt");
            fileReader = new FileReader(changesFile);

            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine())
            {
                String change = scanner.nextLine();

                String[] infos = change.split("\\s+");

                if (AddPlaylist.equals(infos[0]))
                {
                    Playlist playlist = buildPlaylist(infos);
                    client.addPlaylist(playlist);
                }
                else if (RemovePlaylist.equals(infos[0]))
                {
                    String playlist_id = infos[1];
                    client.removePlaylist(playlist_id);
                }
                else if (AddSong.equals(infos[0]))
                {
                    String playlist_id = infos[1];
                    String song_id = infos[2];

                    client.addSongToPlaylist(playlist_id, song_id);
                }
            }

            System.out.println(client.exportJsonString());

            FileWriter fileWriter = new FileWriter("output.json");
            fileWriter.write(client.exportJsonString());

            fileReader.close();
            fileWriter.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static Playlist buildPlaylist(String[] infos)
    {
        String playlist_id = infos[1];
        String user_id = infos[2];
        List<String> song_ids = new ArrayList<>();

        for (int i = 3; i < infos.length; i++)
        {
            song_ids.add(infos[i]);
        }

        Playlist playlist = new Playlist(playlist_id, user_id, song_ids);
        return playlist;
    }
}
