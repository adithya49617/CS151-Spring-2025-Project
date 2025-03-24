package service;

import java.io.*;
import java.util.*;
import model.Artist;
import utility.MusicUtility;

public class ArtistService {
    private List<Artist> artists = new ArrayList<>();
    private static final String FILE_PATH = "src/data/artists.csv";

    public ArtistService() {
        loadArtists();
    }

    // Load artists from CSV data file for usage
    private void loadArtists() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String artistRecordFromFile;
            while ((artistRecordFromFile = br.readLine()) != null) {
                String[] artistData = artistRecordFromFile.split(",");
                artists.add(new Artist(
                        artistData[0], // artistId
                        artistData[1], // artistDOB
                        artistData[2], // artistFirstName
                        artistData[3], // artistLastName
                        artistData[4], // artistEmail
                        artistData[5]  // artistGender
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save artists to CSV data file
    private void saveArtists() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Artist artist : artists) {
                bw.write(artist.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAritistExists(String artistId) {
        boolean exists = false;
        for (Artist tempArtist : artists) {
            if (tempArtist.getArtistId().equals(artistId)) {
                System.out.println(tempArtist);
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    // Add a new artist
    public String createArtist() {
        String artistID = MusicUtility.readString("Enter artist ID: "); // Artist ID
        String artistDob = MusicUtility.readString("Enter artist date of birth: "); // Artist DOB
        String artistFirstName = MusicUtility.readString("Enter artist first name: "); // Artist First Name
        String artistLastName = MusicUtility.readString("Enter artist last name: "); // Artist Last Name
        String artistEmail = MusicUtility.readString("Enter artist Email: "); // Artist Email
        String artistGender = MusicUtility.readString("Enter artist gender: "); // Artist gender

        artists.add(new Artist(artistID, artistDob, artistFirstName, artistLastName, artistEmail, artistGender));
        saveArtists();
        System.out.println("Artist added successfully!");
        return artistID;
    }

    // Search artist by ID
    public void searchArtistById() {
        String artistId = MusicUtility.readString("Enter artist ID: "); // Artist ID

        for (Artist tempArtist : artists) {
            if (tempArtist.getArtistId().equals(artistId)) {
                System.out.println(tempArtist);
                return;
            }
        }
        System.out.println("Artist not found.");
    }

    // Update artist
    public void updateArtistDetails(String artistId) {
        for (Artist artist : artists) {
            if (artist.getArtistId().equals(artistId)) {
                artist.setArtistFirstName(MusicUtility.readString("Enter First Name to Update: "));
                artist.setArtistFirstName(MusicUtility.readString("Enter Last Name to Update: "));
                artist.setArtistFirstName(MusicUtility.readString("Enter Email to Update: "));
                saveArtists();
                System.out.println("Artist updated successfully!");
                return;
            }
        }
        System.out.println("Artist not found.");
    }

    public void addSong(String artistId) {
        SongService songService = new SongService();
        songService.addSong(artistId);
    }

    public void editSong(String artistId) {
        SongService songService = new SongService();
        songService.editSong(artistId);
    }

    public void deleteSong(String artistId) {
        SongService songService = new SongService();
        songService.deleteSong(artistId);
    }

    // Manage artist options menu
    public void showArtistMenu(String artistId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Manage Artist");
            System.out.println("--------------------------------");
            System.out.println("1. Update Artist Details");
            System.out.println("2. Add Song");
            System.out.println("3. Edit Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Return to Main Menu\n");

            int artistChoice = MusicUtility.readInt("Enter your choice: ");
            switch (artistChoice) {
                case 1:
                    updateArtistDetails(artistId);
                    break;
                case 2:
                    addSong(artistId);
                    break;
                case 3:
                    editSong(artistId);
                    break;
                case 4:
                    deleteSong(artistId);
                    break;
                case 5:
                    System.out.println("Exiting from Artist Service");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
