package src.Contoller;

import src.Admin;
import java.util.Scanner;

/*
 * This program allows the user to proceed to the Admin Menu only by pressing the Enter key and the Q key.
 */
public class FinalChoice {

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";

    public FinalChoice() {
        exitOptions();
    }

    public void exitOptions() {
        Scanner scanner = new Scanner(System.in);
        String finalChoice;

        do {
            System.out.print(ANSI_YELLOW + "\nPress Enter to return to Admin menu or Q to quit: " + ANSI_RESET);
            finalChoice = scanner.nextLine();

            if (finalChoice.isEmpty()) {
                // User pressed Enter, return to Admin menu
                System.out.println(ANSI_YELLOW + "Returning to Admin menu..." + ANSI_RESET);
                new WaitingTime();
                new ClearScreen();
                new Admin();
                return;
            } else if (finalChoice.equalsIgnoreCase("Q")) {
                // User entered Q/q, exit program
                System.out.println(ANSI_YELLOW + "Exiting program..." + ANSI_RESET);
                new WaitingTime();
                System.exit(0);
            } else {
                // Invalid input
                System.out.println(
                        ANSI_RED + "Invalid choice! Please press Enter to continue or Q to quit." + ANSI_RESET);
            }
        } while (!finalChoice.isEmpty() && !finalChoice.equalsIgnoreCase("Q"));
    }
}
