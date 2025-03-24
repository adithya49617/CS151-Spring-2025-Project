package service;

import java.io.*;
import java.util.*;
import model.Playlist;
import model.User;
import utility.MusicUtility;

public class PlaylistService {
    private List<Playlist> playlists = new ArrayList<>();
    private static final String FILE_PATH = "src/data/playlists.csv";

    public PlaylistService() {
        loadPlaylists();
    }

    //Load play lists from csv file
    private void loadPlaylists() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String playlistFromFile;
            while ((playlistFromFile = br.readLine()) != null) {
                String[] playlistData = playlistFromFile.split(",");

                String playlistId = playlistData[0];
                String playlistName = playlistData[1];
                String playlistCreator = playlistData[2];
                String playlistGenre = playlistData[3];
                String playlistUserId = playlistData[4];
                String[] playlistSongIds = playlistData[5].split(",");

                playlists.add(new Playlist(
                        playlistId, //playlistId
                        playlistName, //playlistName
                        playlistCreator, //playlistCreator
                        playlistGenre, //playlistGenre
                        playlistUserId, //userId
                        playlistSongIds //songIds
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save playlists to CSV file
    private void savePlaylists() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("PlaylistID,PlaylistName,PlaylistCreator,PlaylistGenre,UserID,SongIDs");
            writer.newLine();
            for (Playlist playlist : playlists) {
                String songList = String.join("|", playlist.getSongs());
                writer.write(String.format("%s,%s,%s,%s,%s,%s",
                        playlist.getPlaylistId(),
                        playlist.getPlaylistName(),
                        playlist.getPlaylistCreator(),
                        playlist.getPlaylistGenre(),
                        playlist.getUserId(),
                        songList
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create new playlist
    public void addPlaylist(String userID) {
        String playListID = MusicUtility.readString("Enter PlayList ID: ");
        String playListName = MusicUtility.readString("Enter PlayList Name: ");
        String PlayListCreator = MusicUtility.readString("Enter PlayList Creator: ");
        String playListGenre = MusicUtility.readString("Enter PlayList Genre: ");
        UserService userService = new UserService();

        SongService songService = new SongService();

        String userEnteredSongIDs = null;
        String userResponse = "Y";
        int songCount = 0;
        while (userResponse.equalsIgnoreCase("Y")) {
            String tempSongID = MusicUtility.readString("Enter Song ID: ");
            if(songService.checkSongExists(tempSongID) == false) {
                System.out.print("Song ID does not exist. ");
            }
            else {
                if(songCount == 0) {
                    userEnteredSongIDs =  tempSongID ;
                } else {
                    userEnteredSongIDs = userEnteredSongIDs + "|" + tempSongID ;
                }
                songCount++ ;
            }
            userResponse = MusicUtility.readString("Do you want to add another song (Y/N)? : ");
        }
        String[] listOfSongsToAdd = userEnteredSongIDs.toString().split("\\|");

        //Add play list
        playlists.add(new Playlist(playListID, playListName, PlayListCreator, playListGenre, userID,listOfSongsToAdd ));
        savePlaylists();
        System.out.println("Playlist added successfully!");
    }

    // Edit playlist data
    public void editPlaylist(String userId) {
        String id = MusicUtility.readString("Enter Playlist ID to edit: ");

        for (Playlist p : playlists) {
            if (p.getPlaylistId().equals(id)) {
                if (p.getUserId().equals(userId)) {
                    p.setPlaylistName(MusicUtility.readString("Enter new Playlist Name: "));
                    p.setPlaylistCreator(MusicUtility.readString("Enter new Playlist Creator: "));
                    p.setPlaylistGenre(MusicUtility.readString("Enter new Playlist Genre: "));
                    savePlaylists();
                    System.out.println("Playlist updated successfully!");
                    return;
                }
                else {
                    System.out.println("Playlist doesn't belong to the user!");
                }
            }
        }
        System.out.println("Playlist not found.");
    }

    // Delete playlist
    public void deletePlaylist(String userId) {
        String id = MusicUtility.readString("Enter Playlist ID to delete: ");

        Iterator<Playlist> iterator = playlists.iterator();
        while (iterator.hasNext()) {
            Playlist p = iterator.next();
            if (p.getPlaylistId().equals(id)) {
                if(p.getUserId().equals(userId)) {
                    iterator.remove();
                    savePlaylists();
                    System.out.println("Playlist deleted successfully!");
                    return;
                }
                else {
                    System.out.println("Playlist doesn't belong to the user!");
                }
            }
        }
        System.out.println("Playlist not found.");
    }

    // View playlist details
    public void viewPlaylists(String userId) {
        String playListId = MusicUtility.readString("Enter Playlist ID to view details: ");

        for (Playlist p : playlists) {
            if (p.getPlaylistId().equals(playListId)) {
                if(p.getUserId().equals(userId)) {
                    p.displayPlaylistInfo();
                } else {
                    System.out.println("Playlist doesn't belong to the user!");
                }

            }
        }
    }

    // Playlist management menu
    public void managePlaylists(String userId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nManage Playlist");
            System.out.println("--------------------------------");
            System.out.println("1. Create Playlist");
            System.out.println("2. Edit Playlist");
            System.out.println("3. Delete Playlist");
            System.out.println("4. View Playlist details");
            System.out.println("5. Return to Main Menu");
            int choice = MusicUtility.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addPlaylist(userId);
                    break;
                case 2:
                    editPlaylist(userId);
                    break;
                case 3:
                    deletePlaylist(userId);
                    break;
                case 4:
                    viewPlaylists(userId);
                    break;
                case 5:
                    System.out.println("Exiting Playlist Service...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}

