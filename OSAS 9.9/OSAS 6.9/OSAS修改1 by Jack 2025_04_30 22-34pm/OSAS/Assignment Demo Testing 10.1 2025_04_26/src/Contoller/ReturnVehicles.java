package src.Contoller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import src.Rental;
import src.Vehicle;
import src.VehicleOperations;

public class ReturnVehicles {
    private String customerID;
    private Scanner scanner;
    private VehicleOperations vehicleManager;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public ReturnVehicles(String customerID) {
        this.customerID = customerID;
        this.scanner = new Scanner(System.in);
        this.vehicleManager = new VehicleOperations();

        processReturn();
    }

    private void processReturn() {
        System.out.println(ANSI_YELLOW + "\n===== Vehicle Return System =====" + ANSI_RESET);

        // Get active rentals
        ArrayList<Rental> activeRentals = vehicleManager.getActiveRentals(customerID);

        if (activeRentals.isEmpty()) {
            System.out.println(ANSI_RED + "You don't have any active rentals to return." + ANSI_RESET);
            return;
        }

        // Display active rentals
        System.out.println(ANSI_GREEN + "\nYour Active Rentals:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "-------------------" + ANSI_RESET);

        for (int i = 0; i < activeRentals.size(); i++) {
            Rental rental = activeRentals.get(i);
            Vehicle vehicle = vehicleManager.getVehicleById(rental.getCarID());

            if (vehicle != null) {
                System.out.println(ANSI_GREEN + (i + 1) + ". Rental ID: " + rental.getRentalID() +
                        " | Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() +
                        " | Rented on: " + rental.getRentalDate() +
                        " | For: " + rental.getDays() + " days" + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + (i + 1) + ". Rental ID: " + rental.getRentalID() +
                        " | Vehicle ID: " + rental.getCarID() +
                        " | Rented on: " + rental.getRentalDate() +
                        " | For: " + rental.getDays() + " days" + ANSI_RESET);
            }
        }

        // Get rental selection
        int selection = -1;
        while (selection < 1 || selection > activeRentals.size()) {
            System.out.print(ANSI_YELLOW + "\nSelect a rental to return (1-" + activeRentals.size()
                    + ") or 0 to cancel: " + ANSI_RESET);
            try {
                selection = Integer.parseInt(scanner.nextLine());
                if (selection == 0) {
                    System.out.println(ANSI_YELLOW + "Return process canceled." + ANSI_RESET);
                    return;
                }
                if (selection < 1 || selection > activeRentals.size()) {
                    System.out.println(ANSI_RED + "Invalid selection. Please try again." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
            }
        }

        // Get selected rental
        Rental selectedRental = activeRentals.get(selection - 1);
        Vehicle vehicle = vehicleManager.getVehicleById(selectedRental.getCarID());

        // Show return summary
        System.out.println(ANSI_YELLOW + "\n===== Return Summary =====" + ANSI_RESET);
        if (vehicle != null) {
            System.out.println(
                    ANSI_GREEN + "Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getYear()
                            + ")" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Rental Rate: $" + vehicle.getRentalRate() + " per day" + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "Vehicle ID: " + selectedRental.getCarID() + ANSI_RESET);
        }
        System.out.println(ANSI_GREEN + "Rental Date: " + selectedRental.getRentalDate() + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Rental Duration: " + selectedRental.getDays() + " days" + ANSI_RESET);

        if (vehicle != null) {
            double totalCost = selectedRental.calculateTotalCost(vehicle.getRentalRate());
            System.out.println(ANSI_GREEN + "Total Cost: $" + totalCost + ANSI_RESET);
        }

        // Confirm return with error handling
        boolean confirmed = false;
        while (!confirmed) {
            System.out.print(ANSI_YELLOW + "\nConfirm return? (Y/N): " + ANSI_RESET);
            String confirmInput = scanner.nextLine().trim().toUpperCase(); // Trim and uppercase input

            if (confirmInput.equals("Y")) {
                // Get current date
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String returnDate = currentDate.format(formatter);

                // Process return
                boolean success = vehicleManager.returnVehicle(selectedRental.getRentalID(), returnDate);

                if (success) {
                    System.out.println(ANSI_GREEN + "\nVehicle returned successfully!" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "\nError occurred during the return process. Please try again later."
                            + ANSI_RESET);
                }
                confirmed = true;
            } else if (confirmInput.equals("N")) {
                System.out.println(ANSI_YELLOW + "\nReturn canceled." + ANSI_RESET);
                confirmed = true;
            } else {
                System.out.println(ANSI_RED + "Invalid input. Please enter 'Y' for Yes or 'N' for No." + ANSI_RESET);
            }
        }
    }
}
