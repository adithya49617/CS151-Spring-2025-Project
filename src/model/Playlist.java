package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String playlistId;
    private String playlistName;
    private String playlistCreator;
    private String playlistGenre;
    private String userId;
    private String[] songIds;


    public Playlist(String playlistId, String playlistName, String playlistCreator, String playlistGenre, String userId, String[] songIds) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistCreator = playlistCreator;
        this.playlistGenre = playlistGenre;
        this.userId = userId;
        this.songIds = songIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setSongs(String[] songIds) {
        this.songIds = songIds;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistCreator() {
        return playlistCreator;
    }

    public void setPlaylistCreator(String playlistCreator) {
        this.playlistCreator = playlistCreator;
    }

    public String getPlaylistGenre() {
        return playlistGenre;
    }

    public void setPlaylistGenre(String playlistGenre) {
        this.playlistGenre = playlistGenre;
    }

    public String[] getSongs() {
        return songIds;
    }


    @Override
    public String toString() {

        return playlistId +
                "," + playlistName +
                "," + playlistCreator +
                "," + playlistGenre +
                "," + songIds; //shows # of songs
    }

    public void displayPlaylistInfo() {
        System.out.println("Playlist ID: " + playlistId);
        System.out.println("Name: " + playlistName);
        System.out.println("Creator: " + playlistCreator);
        System.out.println("Genre: " + playlistGenre);
        System.out.println("Songs in the play list are :"  );

        String[] songList = String.join("|", songIds  ).split("\\|");

        for (String tempSongID : songList) {
            System.out.println(tempSongID);
        }
    }
}
