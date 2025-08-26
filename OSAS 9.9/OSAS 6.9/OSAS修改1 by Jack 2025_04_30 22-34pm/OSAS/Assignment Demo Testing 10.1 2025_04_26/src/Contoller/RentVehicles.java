package src.Contoller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import src.PaymentSystem;
import src.Vehicle;
import src.VehicleOperations;

public class RentVehicles {
    private String customerID;
    private Scanner scanner;
    private VehicleOperations vehicleManager;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public RentVehicles(String customerID) {
        this.customerID = customerID;
        this.scanner = new Scanner(System.in);
        this.vehicleManager = new VehicleOperations();

        processRental();
    }

    private void processRental() {
        System.out.println(ANSI_YELLOW + "\n===== Vehicle Rental System =====" + ANSI_RESET);

        // Get available vehicles
        ArrayList<Vehicle> availableVehicles = vehicleManager.getAvailableVehicles();

        if (availableVehicles.isEmpty()) {
            System.out.println(ANSI_RED + "No vehicles are currently available for rent." + ANSI_RESET);
            System.out
                    .println(ANSI_RED + "Please contact an administrator to add vehicles to the system." + ANSI_RESET);
            return;
        }

        // Display available vehicles
        System.out.println(ANSI_GREEN + "\nAvailable Vehicles:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "-------------------" + ANSI_RESET);

        for (int i = 0; i < availableVehicles.size(); i++) {
            Vehicle vehicle = availableVehicles.get(i);
            System.out.println(ANSI_GREEN + (i + 1) + ". " + vehicle.toString() + ANSI_RESET);
        }

        // Get vehicle selection
        int selection = -1;
        while (selection < 1 || selection > availableVehicles.size()) {
            System.out.print(ANSI_YELLOW + "\nSelect a vehicle (1-" + availableVehicles.size() + ") or 0 to cancel: "
                    + ANSI_RESET);
            try {
                selection = Integer.parseInt(scanner.nextLine());
                if (selection == 0) {
                    System.out.println(ANSI_YELLOW + "Rental process canceled." + ANSI_RESET);
                    return;
                }
                if (selection < 1 || selection > availableVehicles.size()) {
                    System.out.println(ANSI_RED + "Invalid selection. Please try again." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
            }
        }

        // Get selected vehicle
        Vehicle selectedVehicle = availableVehicles.get(selection - 1);

        // Get rental duration
        int days = 0;
        while (days < 1) {
            System.out.print(ANSI_YELLOW + "Enter the number of days for rental: " + ANSI_RESET);
            try {
                days = Integer.parseInt(scanner.nextLine());
                if (days < 1) {
                    System.out.println(ANSI_RED + "Please enter a positive number of days." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
            }
        }

        // Calculate total cost
        double totalCost = selectedVehicle.getRentalRate() * days;

        // Show rental summary
        System.out.println(ANSI_YELLOW + "\n===== Rental Summary =====" + ANSI_RESET);
        System.out
                .println(ANSI_GREEN + "Vehicle: " + selectedVehicle.getBrand() + " " + selectedVehicle.getModel() + " ("
                        + selectedVehicle.getYear() + ")" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Rental Rate: $" + selectedVehicle.getRentalRate() + " per day" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Rental Duration: " + days + " days" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Total Cost: $" + totalCost + ANSI_RESET);

        // Confirm rental
        String confirm;
        while (true) {
            System.out.print(ANSI_YELLOW + "\nConfirm rental? (Y/N): " + ANSI_RESET);
            confirm = scanner.nextLine().trim().toUpperCase();
            if (confirm.equals("Y") || confirm.equals("N")) {
                break;
            }
            System.out.println(ANSI_RED + "Invalid input. Please enter 'Y' or 'N'." + ANSI_RESET);
        }

        if (confirm.equalsIgnoreCase("Y")) {
            // Get current date
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String rentalDate = currentDate.format(formatter);

            // Create rental record
            boolean success = vehicleManager.createRentalRecord(customerID, selectedVehicle.getCarID(), rentalDate,
                    days);

            if (success) {
                System.out.println(ANSI_GREEN + "\nVehicle rented successfully!" + ANSI_RESET);
                System.out.println(
                        ANSI_GREEN + "Please keep track of your rental information for future reference." + ANSI_RESET);

                // Create a description for the payment
                String productDescription = selectedVehicle.getBrand() + " " + selectedVehicle.getModel() +
                        " (" + selectedVehicle.getYear() + ") for " + days + " days";

                // Show a waiting animation
                new WaitingTime();

                // Process payment with the correct information and handle the result
                boolean paymentSuccessful = new PaymentSystem().PaymentRental(productDescription, totalCost);
                if (paymentSuccessful) {
                    System.out.println(
                            ANSI_GREEN + "\nPayment processed successfully. Thank you for your rental!" + ANSI_RESET);

                    // Prompt user to press Enter to continue
                    waitForEnterKey();
                } else {
                    System.out.println(
                            ANSI_RED + "\nPayment process was not completed. Please contact support." + ANSI_RESET);

                    // Prompt user to press Enter to continue
                    waitForEnterKey();
                }
            } else {
                System.out.println(
                        ANSI_RED + "\nError occurred during the rental process. Please try again later." + ANSI_RESET);

                // Prompt user to press Enter to continue
                waitForEnterKey();
            }
        } else {
            System.out.println(ANSI_YELLOW + "\nRental canceled." + ANSI_RESET);
        }
    }

    private void waitForEnterKey() {
        System.out.print(ANSI_YELLOW + "\nPress Enter to return to Customer Portal..." + ANSI_RESET);
        new FinalChoice();

    }
}
