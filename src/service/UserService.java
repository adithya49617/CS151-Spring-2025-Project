package service;

import java.io.*;
import java.util.*;

import model.Artist;
import model.User;
import model.UserSong;
import utility.MusicUtility;

public class UserService {
    private List<User> users = new ArrayList<>();
    private static final String FILE_PATH = "src/data/users.csv";

    public UserService() {
        loadusers();
    }

    // Load users from CSV data file for usage
    private void loadusers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String userRecordFromFile;
            while ((userRecordFromFile = br.readLine()) != null) {
                String[] userData = userRecordFromFile.split(",");
                users.add(new User(
                        userData[0], //userId
                        userData[1], //userPassword
                        userData[2], //userFirstName
                        userData[3], //userLastName
                        userData[4], //userEmail
                        userData[5] //userDOB
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save users to CSV data file
    private void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a new user
    public String createUser() {
        String userID = MusicUtility.readString("Enter user ID: "); //User ID
        String userPassword = MusicUtility.readString("Enter user Password: "); //User Password
        String userFirstName = MusicUtility.readString("Enter user fist name: "); //User First Name
        String userLastName = MusicUtility.readString("Enter user last name: "); //User last Name
        String userEmail = MusicUtility.readString("Enter user Email: "); //User Email
        String userDob = MusicUtility.readString("Enter user date of birth: "); //User DOB
        users.add(new User(userID, userPassword, userFirstName, userLastName, userEmail, userDob));
        saveUsers();
        System.out.println("User added successfully!");
        return userID;
    }

    // Search user by ID
    public void searchUserById(Scanner scanner) {
        String userId = MusicUtility.readString("Enter user ID: ");

        for (User tempUser : users) {
            if (tempUser.getUserId().equals(userId)) {
                System.out.println(tempUser);
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Check if user exists
    public boolean checkUserExists(String userId) {
        boolean exists = false;
        for (User tempUser : users) {
            if (tempUser.getUserId().equals(userId)) {
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    // Update user
    public void updateUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUserFirstName(MusicUtility.readString("Enter First Name to update: "));
                user.setUserLastName(MusicUtility.readString("Enter Last Name to update: "));
                user.setUserEmail(MusicUtility.readString("Enter Email to update: "));

                saveUsers();
                System.out.println("User updated successfully!");
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void playSong( String userID) {
        String songId = MusicUtility.readString("Enter song Id to play: ");
        SongService songService = new SongService();
        if(songService.checkSongExists( songId)){

            UserSongService userPlayedSongService = new UserSongService();
            if(!userPlayedSongService.checkUserPlayedSongExists( userID, songId)) {
                userPlayedSongService.addUserPlayedSong(userID, songId);
                System.out.println("Song played successfully!");
            }
        }
    }

    public void getSongsPlayedByUser(String userID) {
        UserSongService userPlayedSongService = new UserSongService();
        List<UserSong> userSongList = userPlayedSongService.getSongsPlayedByUser(userID);
        SongService songService = new SongService();

        for (UserSong userSong : userSongList) {
            System.out.println(userSong.toString());
        }
    }

    public void showSongCount() {
        String songId = MusicUtility.readString("Enter song Id to get play count: ");
        UserSongService userSongService = new UserSongService();
        System.out.println("Played count: " + userSongService.getPlayCountForSong(songId));
    }

    public void searchSongs() {
        SongService songService = new SongService();
        String songText = MusicUtility.readString("Enter title/genre/artist to search: ");
        songService.searchSongsByText(songText);
    }

    public void showUserMenu(String userId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nActions");
            System.out.println("--------------------------------");
            System.out.println("1. Search Songs");
            System.out.println("2. Play a Song");
            System.out.println("3. Show play history");
            System.out.println("4. Update User Details");
            System.out.println("5. Show song play count");
            System.out.println("6. Manage Playlist");
            System.out.println("7. Manage Payments");
            System.out.println("8. Return to Main Menu\n");

            int userChoice = MusicUtility.readInt("Enter your choice: ");

            switch (userChoice) {
                case 1:
                    searchSongs();
                    break;
                case 2:
                    playSong(userId);
                    break;
                case 3:
                    getSongsPlayedByUser(userId);
                    break;
                case 4:
                    updateUser(userId);
                    break;
                case 5:
                    showSongCount();
                    break;
                case 6:
                    PlaylistService playlistService = new PlaylistService();
                    playlistService.managePlaylists(userId);
                    break;
                case 7:
                    PaymentService paymentService = new PaymentService();
                    paymentService.managePayments(userId);
                    break;
                case 8:
                    System.out.println("Return to Main Menu\n");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}