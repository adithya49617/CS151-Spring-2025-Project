package service;

import java.io.*;
import java.util.*;
import model.Song;
import service.ArtistService;
public class SongService {
    private List<Song> songs = new ArrayList<>();
    private static final String FILE_PATH = "src/data/songs.csv";

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

    // Add a new song
    public void addSong(Scanner scanner) {
        ArtistService artistService = new ArtistService();

        System.out.print("Enter Song ID: ");
        String songId = scanner.nextLine();

        System.out.print("Enter Song Title: ");
        String songTitle = scanner.nextLine();

        System.out.print("Enter Song Length (mm:ss): ");
        String songLength = scanner.nextLine();

        boolean artistCheck = true;
        String songArtist = null;
        while (artistCheck == true) {
            System.out.print("Enter Artist ID: ");
            songArtist = scanner.nextLine();
            if (artistService.checkAritistExists(songArtist) == false) {
                System.out.print("Artist ID does not exist. ");
                artistCheck = true;
            } else {
                artistCheck = false;
            }
        }


        System.out.print("Enter Song Genre: ");
        String songGenre = scanner.nextLine();

        songs.add(new Song(songId, songTitle, songLength, songArtist, songGenre));
        saveSongs();
        System.out.println("Song added successfully!");
    }

    // Delete a song by ID
    public void deleteSong(Scanner scanner) {
        System.out.print("Enter Song ID to delete: ");
        String songId = scanner.nextLine();

        Iterator<Song> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getSongId().equals(songId)) {
                iterator.remove();
                saveSongs();
                System.out.println("Song deleted successfully!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    // Edit all song details
    public void editSong(Scanner scanner) {
        System.out.print("Enter Song ID to edit: ");
        String songId = scanner.nextLine();

        for (Song song : songs) {
            if (song.getSongId().equals(songId)) {
                System.out.print("Enter new Song Title: ");
                song.setSongTitle(scanner.nextLine());

                System.out.print("Enter new Song Length: ");
                song.setSongLength(scanner.nextLine());

                System.out.print("Enter new Song Artist: ");
                song.setSongArtist(scanner.nextLine());

                System.out.print("Enter new Song Genre: ");
                song.setSongGenre(scanner.nextLine());

                saveSongs();
                System.out.println("Song updated successfully!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    // Rename only the song title
    public void renameSong(Scanner scanner) {
        System.out.print("Enter Song ID to rename: ");
        String songId = scanner.nextLine();

        for (Song song : songs) {
            if (song.getSongId().equals(songId)) {
                System.out.print("Enter new Song Title: ");
                song.setSongTitle(scanner.nextLine());
                saveSongs();
                System.out.println("Song title updated successfully!");
                return;
            }
        }
        System.out.println("Song not found.");
    }

    // Menu to manage songs
    public void manageSongs() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Song Management ---");
            System.out.println("1. Add Song");
            System.out.println("2. Delete Song");
            System.out.println("3. Edit Song");
            System.out.println("4. Rename Song Title");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addSong(scanner);
                        break;
                    case 2:
                        deleteSong(scanner);
                        break;
                    case 3:
                        editSong(scanner);
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
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid token
            }
        }
    }
}
