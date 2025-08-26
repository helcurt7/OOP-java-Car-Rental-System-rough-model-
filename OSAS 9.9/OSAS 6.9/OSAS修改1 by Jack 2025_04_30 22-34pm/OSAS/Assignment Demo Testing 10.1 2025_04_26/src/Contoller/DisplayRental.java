package src.Contoller;

import java.util.ArrayList;
import java.util.Scanner;

import src.Rental;
import src.Vehicle;
import src.VehicleOperations;

public class DisplayRental {
    private Scanner scanner;
    private VehicleOperations vehicleOperations;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public DisplayRental() {
        this.scanner = new Scanner(System.in);
        this.vehicleOperations = new VehicleOperations();

        displayRentalInfo();
    }

    private void displayRentalInfo() {
        System.out.println(ANSI_YELLOW + "\n===== Rental Information =====" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "1. Display All Active Rentals" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "2. Display Rental History by Customer ID" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "3. Back" + ANSI_RESET);
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                displayAllActiveRentals();
                break;
            case 2:
                displayRentalsByCustomer();
                break;
            case 3:
                return;
            default:
                System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                displayRentalInfo();
        }
    }

    private void displayAllActiveRentals() {
        System.out.println(ANSI_YELLOW + "\n===== All Active Rentals =====" + ANSI_RESET);

        // This method would need access to all customer rentals
        // However, in the current implementation, we can only get rentals by customer
        // ID.
        System.out.print("Enter customer ID to view active rentals: ");
        String customerID = scanner.nextLine().trim();

        ArrayList<Rental> activeRentals = vehicleOperations.getActiveRentals(customerID);

        if (activeRentals.isEmpty()) {
            System.out.println(ANSI_RED + "No active rentals found for customer ID: " + customerID + ANSI_RESET);
            return;
        }

        displayRentals(activeRentals);
    }

    private void displayRentalsByCustomer() {
        System.out.print("Enter customer ID: ");
        String customerID = scanner.nextLine().trim();

        ArrayList<Rental> rentals = vehicleOperations.getCustomerRentals(customerID);

        if (rentals.isEmpty()) {
            System.out.println(ANSI_RED + "No rental history found for customer ID: " + customerID + ANSI_RESET);
            return;
        }

        System.out
                .println(ANSI_YELLOW + "\n===== Rental History for Customer ID: " + customerID + " =====" + ANSI_RESET);
        displayRentals(rentals);
    }

    private void displayRentals(ArrayList<Rental> rentals) {
        System.out.println(ANSI_YELLOW + "\n--------------------------------------------" + ANSI_RESET);
        for (Rental rental : rentals) {
            System.out.println(rental.toString());

            // Get and display vehicle details
            Vehicle vehicle = vehicleOperations.getVehicleById(rental.getCarID());
            if (vehicle != null) {
                System.out.println(ANSI_GREEN + "Vehicle Details:" + ANSI_RESET);
                System.out.println(vehicle.toString());
                System.out.println(
                        ANSI_GREEN + "Total Cost: $" + rental.calculateTotalCost(vehicle.getRentalRate()) + ANSI_RESET);
            }
            System.out.println(ANSI_YELLOW + "--------------------------------------------" + ANSI_RESET);
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print(ANSI_RED + "Please enter a valid number: " + ANSI_RESET);
            return getIntInput();
        }
    }
}
