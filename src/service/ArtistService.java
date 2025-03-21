package service;

import java.io.*;
import java.util.*;
import model.Artist;

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
    public void addArtist(Scanner scanner) {
        System.out.print("Enter artist ID: ");
        String artistID = scanner.nextLine();  // Artist ID

        System.out.print("Enter artist date of birth: ");
        String artistDob = scanner.nextLine();   // Artist DOB

        System.out.print("Enter artist first name: ");
        String artistFirstName = scanner.nextLine();   // Artist First Name

        System.out.print("Enter artist last name: ");
        String artistLastName = scanner.nextLine();   // Artist Last Name

        System.out.print("Enter artist Email: ");
        String artistEmail = scanner.nextLine();   // Artist Email

        System.out.print("Enter artist gender: ");
        String artistGender = scanner.nextLine();   // Artist Gender

        artists.add(new Artist(artistID, artistDob, artistFirstName, artistLastName, artistEmail, artistGender));
        saveArtists();
        System.out.println("Artist added successfully!");
    }

    // Search artist by ID
    public void searchArtistById(Scanner scanner) {
        System.out.print("Enter Artist ID: ");
        String artistId = scanner.nextLine();

        for (Artist tempArtist : artists) {
            if (tempArtist.getArtistId().equals(artistId)) {
                System.out.println(tempArtist);
                return;
            }
        }
        System.out.println("Artist not found.");
    }

    // Update artist
    public void updateArtist(Scanner scanner) {
        System.out.print("Enter Artist ID to update: ");
        String artistId = scanner.nextLine();

        for (Artist artist : artists) {
            if (artist.getArtistId().equals(artistId)) {
                System.out.print("Enter First Name to Update: ");
                artist.setArtistFirstName(scanner.nextLine());

                System.out.print("Enter Last Name to Update: ");
                artist.setArtistLastName(scanner.nextLine());

                System.out.print("Enter Email to Update: ");
                artist.setArtistEmail(scanner.nextLine());

                saveArtists();
                System.out.println("Artist updated successfully!");
                return;
            }
        }
        System.out.println("Artist not found.");
    }

    // Manage artist options menu
    public void manageArtist() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Manage Artist ---");
            System.out.println("1. Add Artist");
            System.out.println("2. Search Artist");
            System.out.println("3. Update Artist");
            System.out.println("4. Return to Main Menu\n");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int artistChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (artistChoice) {
                    case 1:
                        addArtist(scanner);
                        break;
                    case 2:
                        searchArtistById(scanner);
                        break;
                    case 3:
                        updateArtist(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting from Artist Service");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
    }
}
