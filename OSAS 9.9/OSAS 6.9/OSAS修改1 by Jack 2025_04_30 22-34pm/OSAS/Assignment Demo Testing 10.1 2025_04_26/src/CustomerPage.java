package src;

import src.Contoller.RentVehicles;
import src.Contoller.ReturnVehicles;
import src.Contoller.ShowProfile;
import src.Contoller.UpdateProfile;
import src.Contoller.ViewRentHistory;

public class CustomerPage implements UserInterface {

    private String ID;
    private boolean run;

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public CustomerPage(String ID) {
        this.ID = ID;
        this.run = true;
        displayMenu();
    }

    @Override
    public void displayMenu() {
        while (run) {
            showMenuOptions();
            int choice = getValidChoice();
            handleMenuChoice(choice);
        }
    }

    @Override
    public void showMenuOptions() {
        System.out.println("");
        System.out.println("");
        System.out.println(YELLOW + "╔══════════════════════════════════╗" + RESET);
        System.out.println(YELLOW + "║       ~ Customer Portal ~        ║" + RESET);
        System.out.println(YELLOW + "╠══════════════════════════════════╣" + RESET);
        System.out.println(BLUE + "║ 1.  Show Profile                 ║" + RESET);
        System.out.println(BLUE + "║ 2.  Update Profile               ║" + RESET);
        System.out.println(GREEN + "║ 3.  Rent Vehicles                ║" + RESET);
        System.out.println(GREEN + "║ 4.  Return a Vehicle             ║" + RESET);
        System.out.println(PURPLE + "║ 5.  View Rental History          ║" + RESET);
        System.out.println(RED + "║ 0.  Back to Main Menu            ║" + RESET);
        System.out.println(YELLOW + "╚══════════════════════════════════╝" + RESET);
        System.out.print(CYAN + " Enter your choice (0-5): " + RESET);
    }

    @Override
    public boolean handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                showProfile();
                return true;
            case 2:
                updateProfile();
                return true;
            case 3:
                rentVehicles();
                return true;
            case 4:
                returnVehicles();
                return true;
            case 5:
                viewRentHistory();
                return true;
            case 0:
                returnToMainMenu();
                return false; // Exit the menu loop
            default:
                System.out.println(RED + "Invalid choice. Please try again." + RESET);
                return true;
        }
    }

    @Override
    public int getValidChoice() {
        return MenuHandler.getValidIntInput();
    }

    private void showProfile() {
        System.out.println(GREEN + "\nShowing profile for customer ID: " + ID + RESET);
        new ShowProfile(ID);
    }

    private void updateProfile() {
        System.out.println(YELLOW + "\nUpdating profile for customer ID: " + ID + RESET);
        new UpdateProfile(ID);
    }

    private void rentVehicles() {
        System.out.println(BLUE + "\nRenting Vehicles" + RESET);
        new RentVehicles(ID);
    }

    private void returnVehicles() {
        System.out.println(PURPLE + "\nReturning Vehicles" + RESET);
        new ReturnVehicles(ID);
    }

    private void viewRentHistory() {
        System.out.println(CYAN + "\nViewing Rent History for customer ID: " + ID + RESET);
        new ViewRentHistory(ID);
    }

    private void returnToMainMenu() {
        System.out.println(RED + "\nReturning to main menu..." + RESET);
        new Main();
    }
}
