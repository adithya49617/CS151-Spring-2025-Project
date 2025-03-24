package service;

import java.io.*;
import java.util.*;
import model.Song;
import model.User;
import model.UserSong;
import service.ArtistService;
import utility.MusicUtility;

public class SongService {
    private List<Song> songs = new ArrayList<>();
    private static final String FILE_PATH = "src/data/songs.csv";

    private List<UserSong> userSongs = new ArrayList<>();
    private static final String USERSONGS_FILE_PATH = "src/data/userplayedsongs.csv";

    public SongService() {
        loadSongs();
    }

    // Load songs from CSV data file
    private void loadSongs() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String songRecord;
            while ((songRecord = br.readLine()) != null) {
                String[] songData = songRecord.split(",");
                songs.add(new Song(
                        songData[0], // songId
                        songData[1], // songTitle
                        songData[2], // songLength
                        songData[3], // songArtist
                        songData[4]  // songGenre
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save songs to CSV data file
    private void saveSongs() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Song song : songs) {
                bw.write(song.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a new song by Artist
    public void addSong(String artistId) {
        ArtistService aritstService = new ArtistService();
        String songId = MusicUtility.readString("Enter Song ID: ");
        String songTitle = MusicUtility.readString("Enter Song Title: ");
        String songLength = MusicUtility.readString("Enter Song Length (mm:ss): ");

        //checking if artist ID exists in artist csv file
        boolean artistCheck = true;
        String songArtist = null;
        while (artistCheck == true) {
            if (aritstService.checkAritistExists(artistId) == false) {
                System.out.print("Artist ID does not exist. ");
                artistCheck = true;
            } else {
                artistCheck = false;
            }
        }
        String songGenre = MusicUtility.readString("Enter Song Genre: ");
        songs.add(new Song(songId, songTitle, songLength, artistId, songGenre));
        saveSongs();
        System.out.println("Song added successfully!");
    }

    // Delete a song by ID
    public void deleteSong(String artistId) {
        String songId = MusicUtility.readString("Enter Song ID: ");

        Iterator<Song> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getSongArtist().equals(artistId)) {
                if (song.getSongId().equals(songId)) {
                    iterator.remove();
                    saveSongs();
                    System.out.println("Song deleted successfully!");
                    return;
                }
            } else {
                System.out.println("This song does not belong to this artist!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    // Edit all song details
    public void editSong(String artistId) {
        String songId = MusicUtility.readString("Enter Song ID to edit: ");

        for (Song song : songs) {
            if (song.getSongId().equals(songId)) {
                song.setSongTitle(MusicUtility.readString("Enter new Song Title: "));
                song.setSongTitle(MusicUtility.readString("Enter new Song Length: "));
                song.setSongTitle(artistId);
                song.setSongTitle(MusicUtility.readString("Enter new Song Genre: "));

                saveSongs();
                System.out.println("Song updated successfully!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    // Rename only the song title
    public void renameSong(Scanner scanner) {
        String songId = MusicUtility.readString("Enter Song ID to rename: ");
        for (Song song : songs) {
            if (song.getSongId().equals(songId)) {
                song.setSongTitle(MusicUtility.readString("Enter new Song Title: "));
                saveSongs();
                System.out.println("Song title updated successfully!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    // Check if Song exists
    public boolean checkSongExists(String songId) {
        boolean exists = false;
        for (Song tempSong : songs) {
            if (tempSong.getSongId().equals(songId)) {
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    public void searchSongsByText(String searchText) {
        for (Song tempSong : songs) {
            if (tempSong.getSongArtist().contains(searchText) ||
                    tempSong.getSongGenre().contains(searchText) ||
                    tempSong.getSongTitle().contains(searchText) )
            {
                System.out.println(tempSong);
            }
        }
    }

    public void searchSongsByID(String songId) {
        for (Song tempSong : songs) {
            if (tempSong.getSongId().equals(songId))
            {
                System.out.println(tempSong);
            }
        }
    }


    // Menu to manage songs
    public void manageSongs(String artistID) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nManage Song");
            System.out.println("--------------------------------");
            System.out.println("1. Add Song");
            System.out.println("2. Delete Song");
            System.out.println("3. Edit Song");
            System.out.println("4. Rename Song Title");
            System.out.println("5. Return to Main Menu");

            int choice = MusicUtility.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addSong(artistID);
                    break;
                case 2:
                    deleteSong(artistID);
                    break;
                case 3:
                    editSong(artistID);
                    break;
                case 4:
                    renameSong(scanner);
                    break;
                case 5:
                    System.out.println("Exiting Song Service...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                }
        }
    }
}
