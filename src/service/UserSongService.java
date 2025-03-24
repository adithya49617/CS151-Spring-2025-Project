package service;

import model.Song;
import model.User;
import model.UserSong;
import utility.MusicUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSongService {
    private List<UserSong> userSongs = new ArrayList<>();
    private static final String FILE_PATH = "src/data/userplayedsongs.csv";

    public UserSongService() {
        loadUserSongs();
    }

    // Load user played songs from CSV data file
    private void loadUserSongs() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String userPlayedSongs;
            while ((userPlayedSongs = br.readLine()) != null) {
                String[] userPlayedSongData = userPlayedSongs.split(",");
                userSongs.add(new UserSong(
                        userPlayedSongData[0], // songId
                        userPlayedSongData[1] // songTitle
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save User Played Songs to CSV data file
    private void saveUserPlayedSongs() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (UserSong userPlaySong : userSongs) {
                bw.write(userPlaySong.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if Song exists
    public boolean checkUserPlayedSongExists(String userId, String songId) {
        boolean exists = false;
        for (UserSong userPlaySong : userSongs) {
            if (userPlaySong.getSongId().equals(songId) && userPlaySong.getUserId().equals(userId)) {
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    public void addUserPlayedSong(String userId, String songId) {
        userSongs.add(new UserSong(userId, songId));
        saveUserPlayedSongs();
        System.out.println("Tracked user played song successfully!");

    }

    public int getPlayCountForSong(String songId) {
        int playCount = 0;
        for (UserSong userPlaySong : userSongs) {
            if (userPlaySong.getSongId().equals(songId)) {
                playCount++;
            }
        }
        return playCount;
    }

    public List<UserSong> getSongsPlayedByUser(String userID) {
        List<UserSong> tempUserSongs = new ArrayList<>();
        for (UserSong userPlaySong : userSongs) {
            if (userPlaySong.getUserId().equals(userID)) {
                tempUserSongs.add(userPlaySong);
            }
        }
        return tempUserSongs;
    }

}
