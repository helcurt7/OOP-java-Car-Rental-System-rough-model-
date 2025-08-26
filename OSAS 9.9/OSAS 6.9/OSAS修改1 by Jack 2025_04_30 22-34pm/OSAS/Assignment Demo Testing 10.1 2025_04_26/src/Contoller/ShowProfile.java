package src.Contoller;

import java.util.Scanner;
import src.CustomerOperations;
import src.CustomerPage;
// import src.Main;

public class ShowProfile {
    private String ID;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public ShowProfile(String ID) {
        this.ID = ID;
        displayOptions();
    }

    public void displayOptions() {
        System.out.println(ANSI_YELLOW + "\n----- Profile Options -----" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. View Profile Info" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2. Back to Customer Page" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + "Enter your choice (1-2): " + ANSI_RESET);

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        handleOption(choice);
    }

    public void handleOption(int choice) {
        switch (choice) {
            case 1:
                showProfileInfo();
                break;
            case 2:
                goBack();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
                displayOptions();
                break;
        }
    }

    private void showProfileInfo() {
        CustomerOperations operatons = new CustomerOperations();
        String profile = operatons.getProfileInfo(ID);
        System.out.println(ANSI_YELLOW + "\n ---- Profile Information ----" + ANSI_RESET);
        System.out.println(ANSI_GREEN + profile + ANSI_RESET);

        // After displaying, return to options
        displayOptions();
    }

    private void goBack() {
        System.out.println(ANSI_YELLOW + "Returning to Customer Page..." + ANSI_RESET);
        new CustomerPage(ID);
        new FinalChoice();
    }
}
