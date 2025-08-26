package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import src.Contoller.AddCustomer;
import src.Contoller.AddStaff;
import src.Contoller.AddVehicle;
import src.Contoller.DeleteCustomer;
import src.Contoller.DeleteVehicle;
import src.Contoller.Display;
import src.Contoller.DisplayRental;
import src.Contoller.SearchCustomer;
import src.Contoller.UpdateVehicleCost;
import src.Contoller.ViewAvailableVehicles;
import src.Contoller.ViewStaff;

public class Admin extends Person implements UserInterface {

    private String username;
    private String password;
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String BRIGHT_CYAN = "\u001B[36;1m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BRIGHT_WHITE = "\u001B[37;1m";
    private boolean run;
    private AdminOperations adminOperations;

    public Admin() {
        this.run = true;
        this.adminOperations = new AdminOperations();
        displayMenu();
    }

    @Override
    public String getRole() {
        return "Admin";
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
        System.out.println(CYAN + "\n\n╔════════════════════════════════════════════════╗" + RESET);
        System.out.println(
                CYAN + "║" + BRIGHT_CYAN + "          ~  ADMIN CONTROL PANEL  ~             " + CYAN + "║" + RESET);
        System.out.println(CYAN + "╠════════════════════════════════════════════════╣" + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "1.  Add Customer Profile                       " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "2.  View All Customers                         " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "3.  Search Customer Info                       " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "4.  Delete Customer Info                       " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "5.  Add Vehicle                                " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "6.  Display Rental Information                 " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "7.  Update Vehicle Rental Rate                 " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "8.  Delete Vehicle                             " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "9.  View Available Vehicles                    " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "10. Add Staff Profile                          " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "11. View All Staff                             " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "12. Back to Main Menu                          " + CYAN + "║"
                        + RESET);
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "13. Generate Summary Report                    " + CYAN
                        + "║" + RESET); // Added option
        System.out.println(
                CYAN + "║ " + BRIGHT_WHITE + "0.  Exit                                       " + CYAN + "║"
                        + RESET);
        System.out.print(YELLOW + " ENTER YOUR COMMAND (0-13) ➤ " + RESET);
    }

    @Override
    public boolean handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addCustomer();
                break;
            case 2:
                viewAllCustomers();
                break;
            case 3:
                searchCustomer();
                break;
            case 4:
                deleteCustomer();
                break;
            case 5:
                addVehicle();
                break;
            case 6:
                displayRentalInformation();
                break;
            case 7:
                updateVehicleCost();
                break;
            case 8:
                deleteVehicle();
                break;
            case 9:
                viewAvailableVehicles();
                break;
            case 10:
                addStaff();
                break;
            case 11:
                viewAllStaff();
                break;
            case 12:
                goBack();
                break;
            case 13:
                generateSummaryReport();
                break;
            case 0:
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    @Override
    public int getValidChoice() {
        return MenuHandler.getValidIntInput();
    }

    private void addCustomer() {
        System.out.println(BRIGHT_CYAN + "Adding a new profile....." + RESET);
        new AddCustomer();
    }

    private void viewAllCustomers() {
        System.out.println(BRIGHT_CYAN + "Displaying all customers....." + RESET);
        new Display();
    }

    private void searchCustomer() {
        System.out.println(BRIGHT_CYAN + "Searching for a customer....." + RESET);
        new SearchCustomer();
    }

    private void addVehicle() {
        System.out.println(BRIGHT_CYAN + "Adding a new vehicle..." + RESET);
        new AddVehicle();
    }

    private void deleteCustomer() {
        System.out.println(BRIGHT_CYAN + "Deleting a customer profile..." + RESET);
        new DeleteCustomer();
    }

    private void displayRentalInformation() {
        System.out.println(BRIGHT_CYAN + "Showing Rental Information....." + RESET);
        new DisplayRental();
    }

    private void updateVehicleCost() {
        System.out.println(BRIGHT_CYAN + "Update Vehicle Information....." + RESET);
        new UpdateVehicleCost();
    }

    private void deleteVehicle() {
        System.out.println(BRIGHT_CYAN + "Delete Vehicle Information....." + RESET);
        new DeleteVehicle();
    }

    private void viewAvailableVehicles() {
        System.out.println(BRIGHT_CYAN + "Viewing all available vehicles..." + RESET);
        new ViewAvailableVehicles();
    }

    private void goBack() {
        System.out.println(BRIGHT_CYAN + "Going back to main menu..." + RESET);
        new Main();
    }

    private void addStaff() {
        System.out.println(BRIGHT_CYAN + "Adding new staff..." + RESET);
        new AddStaff();
    }

    private void viewAllStaff() {
        System.out.println(BRIGHT_CYAN + "Viewing all staff..." + RESET);
        new ViewStaff();
    }

    private void generateSummaryReport() {
        System.out.println(BRIGHT_CYAN + "\nGenerating Summary Report...\n" + RESET);

        ArrayList<Customer> allCustomers = adminOperations.viewAllProfiles();
        ArrayList<Staff> allStaff = adminOperations.viewAllStaff();
        ArrayList<Vehicle> allVehicles = adminOperations.viewAllVehicles();

        int totalCustomers = allCustomers.size();
        int totalStaff = allStaff.size();
        int totalVehicles = allVehicles.size();
        int availableVehicles = 0;
        double totalRevenue = 0.0;

        for (Vehicle vehicle : allVehicles) {
            if (vehicle.getStatus().equalsIgnoreCase("Available")) {
                availableVehicles++;
            }
            totalRevenue += vehicle.getRentalRate();
        }

        Map<String, Integer> vehicleCounts = new HashMap<>();
        for (Vehicle vehicle : allVehicles) {
            String model = vehicle.getModel();
            vehicleCounts.put(model, vehicleCounts.getOrDefault(model, 0) + 1);
        }

        System.out.println(BRIGHT_WHITE + "╔════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║              ~ SUMMARY REPORT ~                ║" + RESET);
        System.out.println(BRIGHT_WHITE + "╠════════════════════════════════════════════════╣" + RESET);
        System.out
                .println(CYAN + "║  Total Customers:                    " + String.format("%-10d", totalCustomers)
                        + CYAN + "║" + RESET);
        System.out.println(CYAN + "║  Total Staff:                        " + String.format("%-10d", totalStaff)
                + CYAN + "║" + RESET);
        System.out
                .println(CYAN + "║  Total Vehicles:                     " + String.format("%-10d", totalVehicles)
                        + CYAN + "║" + RESET);
        System.out
                .println(CYAN + "║  Available Vehicles:                 "
                        + String.format("%-10d", availableVehicles)
                        + CYAN + "║" + RESET);
        System.out.println(
                CYAN + "║  Total Revenue Potential:           " + String.format("$%-10.2f", totalRevenue)
                        + CYAN + "║" + RESET);

        for (Map.Entry<String, Integer> entry : vehicleCounts.entrySet()) {
            String formattedEntry = String.format("  %-20s: %-5d", entry.getKey(), entry.getValue());
            System.out.println(CYAN + "║ " + formattedEntry + CYAN + "                  ║" + RESET);
        }

        System.out.println(BRIGHT_WHITE + "╚════════════════════════════════════════════════╝" + RESET);
    }
}
