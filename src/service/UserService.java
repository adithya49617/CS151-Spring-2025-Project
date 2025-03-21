package service;

import java.io.*;
import java.util.*;
import model.User;

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
                        userData[1], //userDOB
                        userData[2], //userFirstName
                        userData[3], //userLastName
                        userData[4], //userEmail
                        userData[5] //userGender
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save students to CSV data file
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
    public void addUser(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();  //User ID

        System.out.print("Enter user date of birth: ");
        String userDob = scanner.nextLine();   //User DOB

        System.out.print("Enter user fist name: ");
        String userFirstName = scanner.nextLine();   //User First Name

        System.out.print("Enter user last name: ");
        String userLastName = scanner.nextLine();   //User Last Name

        System.out.print("Enter user Email: ");
        String userEmail = scanner.nextLine();   //User Email

        System.out.print("Enter user gender: ");
        String userGender = scanner.nextLine();   //User gender

        users.add(new User(userID, userDob, userFirstName, userLastName, userEmail, userGender));
        saveUsers();
        System.out.println("User added successfully!");
    }

    // Search user by ID
    public void searchUserById(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        for (User tempUser : users) {
            if (tempUser.getUserId().equals(userId)) {
                System.out.println(tempUser);
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Update user
    public void updateUser(Scanner scanner) {
        System.out.print("Enter User ID to update: ");
        String userId = scanner.nextLine();

        for (User user : users) {
            if (user.getUserId().equals(userId) ) {
                System.out.print("Enter First Name to Update: ");
                user.setUserFirstName(scanner.nextLine());

                System.out.print("Enter Last Name to Update: ");
                user.setUserLastName(scanner.nextLine());

                System.out.print("Enter Email to Update: ");
                user.setUserEmail(scanner.nextLine());

                saveUsers();
                System.out.println("User updated successfully!");
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void manageUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Manage User");
            System.out.println("\n1. Add User");
            System.out.println("2. Search User");
            System.out.println("3. Update User");
            System.out.println("4. Return to Main Menu\n");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {

                int userChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        addUser(scanner);
                        break;
                    case 2:
                        searchUserById(scanner);
                        break;
                    case 3:
                        updateUser(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting from User Service");

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
