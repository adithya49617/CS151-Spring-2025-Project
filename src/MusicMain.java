import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import service.UserService;
import service.ArtistService;
import service.SongService;
import service.PlaylistService;
import service.PaymentService;
import utility.MusicUtility;

public class MusicMain {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");
        UserService userService = new UserService();
        ArtistService artistService = new ArtistService();
        System.out.println("Welcome to the Music Application!");
        String accessChoice = MusicUtility.readString("Do you already have an account? (Y/N): ");

        String accountID = null;
        Boolean accountExistsFlag ;
        String userType = null;

        if (accessChoice.equals("Y")) //Account exists
        {
            {
                accountID = MusicUtility.readString("Enter your User ID: ");
                String userPassword = MusicUtility.readString("Enter your User Password: ");
                boolean userExists = userService.checkUserExists(accountID);
                boolean artistExists = artistService.checkAritistExists(accountID);

                if(userExists || artistExists) {
                    accountExistsFlag = true;

                    if(accountExistsFlag) {
                        userType = "R";
                    } else {
                        userType = "A";
                    }
                }
                else {
                    accountExistsFlag = false;
                    System.out.println("Invalid User Name or Password, please try again.");
                }
            } while (accountExistsFlag == false);
        }
        else { //Account does not exist
            userType = MusicUtility.readString("Do you want to create a regular account (R) or an Artist account (A)? (R/A): ");
            if(userType.equals("R")) {  //Regular Account
                accountID = userService.createUser();
            } else {    //Artist Account
                accountID = artistService.createArtist();
            }

        }

        if(userType.equals("R")) {
            userService.showUserMenu(accountID);
        } else {
            artistService.showArtistMenu(accountID);
        }
    }
}
