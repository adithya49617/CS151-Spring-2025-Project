//import utility.FileHandler;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import service.UserService;
import service.ArtistService;
import service.SongService;

public class MusicMain {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");
        UserService userService = new UserService();
        ArtistService artistService = new ArtistService();
        SongService songService = new SongService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMusic Management System Menu:");
            System.out.println("1. Manage User");
            System.out.println("2. Manage Artist");
            System.out.println("3. Manage Song");
            System.out.println("4. Manage Playlist");
            System.out.println("5. Manage Payment");
            System.out.println("6. Manage Library");
            System.out.println("7. Exit\n");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userService.manageUser();
                        break;
                    case 2:
                        artistService.manageArtist();
                        break;
                    case 3:
                        songService.manageSongs();
                        break;
                    case 4:
                        //   courseService.manageCourses(scanner);
                        break;
                    case 5:
                        //   gradeService.manageGrades(scanner);
                        break;
                    case 6:
                        //   attendanceService.manageAttendance(scanner);
                        break;
                    case 7:
                        System.out.println("Exiting from main ");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }  else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }


    }
}
