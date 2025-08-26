package src.Contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import src.Admin;

public class ViewStaff {
    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public ViewStaff() {
        displayStaffDetails();
    }

    public void displayStaffDetails() {
        System.out.println(ANSI_YELLOW + "\n===== Staff Details =====" + ANSI_RESET);

        // Check if file exists
        File file = new File("staff.txt");
        if (!file.exists() || file.length() == 0) {
            System.out.println(ANSI_RED + "\nNo staff records found." + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "\n=============================" + ANSI_RESET);

            // Wait for user input before returning to admin menu
            System.out.print(ANSI_YELLOW + "\nPress Enter to return to Admin menu: " + ANSI_RESET);
            new Scanner(System.in).nextLine();
            new Admin();
            return;
        }

        // Read and display staff details
        try (BufferedReader reader = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            int staffCount = 0;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println(ANSI_BLUE + "\n" + line + ANSI_RESET);
                    staffCount++;
                }
            }

            if (staffCount == 0) {
                System.out.println(ANSI_RED + "\nNo staff records found." + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "\nTotal staff records found: " + staffCount + ANSI_RESET);
            }

        } catch (IOException e) {
            System.out.println(ANSI_RED + "\n❌ Error reading staff.txt: " + e.getMessage() + ANSI_RESET);
            // Create the file if it doesn't exist
            try {
                file.createNewFile();
                System.out.println(ANSI_GREEN + "\nCreated new staff.txt file. No records available yet." + ANSI_RESET);
            } catch (IOException ex) {
                System.out.println(ANSI_RED + "\n❌ Failed to create staff.txt: " + ex.getMessage() + ANSI_RESET);
            }
        }

        System.out.println(ANSI_YELLOW + "\n=============================" + ANSI_RESET);

        // Wait for user input before returning to admin menu
        System.out.print(ANSI_YELLOW + "\nPress Enter to return to Admin menu: " + ANSI_RESET);
        new Scanner(System.in).nextLine();
        new FinalChoice();
        new Admin();
    }
}
