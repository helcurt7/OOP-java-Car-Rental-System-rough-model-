package src;

import java.util.Scanner;

/**
 * Helper class that provides common menu functionality
 * This is used to avoid code duplication when implementing the UserInterface
 */
public class MenuHandler {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Run a menu loop with the provided UserInterface implementation
     * @param ui The UserInterface implementation to use
     */
    public static void runMenu(UserInterface ui) {
        boolean running = true;
        
        while (running) {
            ui.showMenuOptions();
            int choice = ui.getValidChoice();
            running = ui.handleMenuChoice(choice);
        }
    }
    
    /**
     * Get valid integer input from the user
     * @return The validated integer choice
     */
    public static int getValidIntInput() {
        int choice = -1;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        return choice;
    }
}