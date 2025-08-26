package src;

import java.util.Scanner;
import src.Contoller.CustomerRegistration;

public class Main {

    // ANSI escape codes for colored output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public Main() {
        System.out.println();
        System.out.println(RED + "           _______" + RESET);
        System.out.println(RED + "           //  ||\\ \\" + RESET);
        System.out.println(RED + "     _____//___||_\\ \\___" + RESET);
        System.out.println(RED + "     )  _          _    \\" + RESET);
        System.out.println(RED + "     |_/ \\________/ \\___|" + RESET);
        System.out.println(RED + "       \\_/        \\_/" + RESET);
        System.out.println();
        System.out.println(YELLOW + "    =========================" + RESET);
        System.out.println(YELLOW + "    |   SPEEDY CAR RENTALS  |" + RESET);
        System.out.println(YELLOW + "    =========================" + RESET);
        System.out.println(GREEN + " ....Your Journey Starts Here...." + RESET);
        System.out.println("");
        System.out.println("");
        System.out.println(CYAN + "Welcome to Car Rental System" + RESET);
        System.out.println("");
        System.out.println(BLUE + "1. Admin" + RESET);
        System.out.println(BLUE + "2. Customer Login" + RESET);
        System.out.println(BLUE + "3. Customer Register" + RESET);
        System.out.println(BLUE + "4. Exit" + RESET);
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validInput = false;

        // Keep looping until user provides valid input
        while (!validInput) {
            System.out.print(PURPLE + "Enter your choice (1-4): " + RESET);

            try {
                // Try to get an integer input
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();

                    // Check if it's a valid option
                    if (choice >= 1 && choice <= 4) {
                        validInput = true; // Valid input, exit the input loop
                    } else {
                        System.out.println(RED + "Invalid choice! Please enter 1, 2, 3 or 4." + RESET);
                    }
                } else {
                    // Non-integer input
                    System.out.println(RED + "Please enter a number (1, 2, 3 or 4)." + RESET);
                    scanner.next(); // Clear the invalid input
                }
            } catch (Exception e) {
                System.out.println(RED + "Error reading input. Please try again." + RESET);
                scanner.nextLine(); // Clear the scanner buffer
            }
        }

        // Now that we have valid input, handle the choice
        handleChoice(choice);
    }

    public void handleChoice(int choice) {
        if (choice == 1) {
            new AdminLogin();
        } else if (choice == 2) {
            new CustomerLogin();
        } else if (choice == 3) {
            new CustomerRegistration();
        } else if (choice == 4) {
            System.out.println(YELLOW + "Thank you for using Car Rental System. Goodbye!" + RESET);
            System.exit(0); // Exit the program
        }
    }
}
