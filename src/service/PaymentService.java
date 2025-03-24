package service;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import model.Payment;
import utility.MusicUtility;

public class PaymentService {
    private List<Payment> payments = new ArrayList<>();
    private static final String FILE_PATH = "src/data/payments.csv";

    public PaymentService() {
        loadPayments();
    }

    private void loadPayments() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                payments.add(new Payment(
                        data[0], // paymentId
                        data[1], // userId
                        data[2], // subscriptionType
                        data[3], // paymentAmount
                        data[4]  // paymentDate
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePayments() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Payment p : payments) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPayment(String paymentUserId) {
        UserService userService = new UserService();
        int counter = 0;


        System.out.println("Select Subscription Type:");
        System.out.println("1. 1-month");
        System.out.println("2. 6-month");
        System.out.println("3. 1-year");
        int subscriptionChoice = MusicUtility.readInt("Choice: ");
        String subscriptionType;
        String amount;

        switch (subscriptionChoice) {
            case 1:
                subscriptionType = "1-month";
                amount = "9.99";
                break;
            case 2:
                subscriptionType = "6-month";
                amount = "49.99";
                break;
            case 3:
                subscriptionType = "1-year";
                amount = "89.99";
                break;
            default:
                System.out.println("Invalid choice. Subscription not added.");
                return;
        }

        Random rand = new Random();
        int number = rand.nextInt(10000);
        String transactionId = "TRANSID-" + number;
        System.out.println(transactionId);

        String transDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));

        payments.add(new Payment(transactionId, paymentUserId, subscriptionType, amount, transDateTime));
        savePayments();
        System.out.println("Subscription payment recorded successfully!");
    }

    public void viewPayments(String paymentUserId) {
        int foundPayment = 0;
        for (Payment p : payments) {
            if (p.getUserId().equals(paymentUserId)) {
                p.displayPaymentInfo();
                System.out.println("-------------------------");
                foundPayment = 1;
            }
        }
        if(foundPayment==0){
            System.out.print("You have no payment records. ");
        }
    }

    public void managePayments(String userId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nManageme Payment");
            System.out.println("--------------------------------");
            System.out.println("1. Add Subscription Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Return to Main Menu");

            int choice = MusicUtility.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addPayment(userId);
                    break;
                case 2:
                    viewPayments(userId);
                    break;
                case 3:
                    System.out.println("Exiting Payment Service...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
