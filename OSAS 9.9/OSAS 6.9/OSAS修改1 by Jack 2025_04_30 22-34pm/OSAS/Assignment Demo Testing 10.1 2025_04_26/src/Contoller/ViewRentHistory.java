package src.Contoller;

import java.util.ArrayList;
import java.util.Scanner;
import src.Rental;
import src.Vehicle;
import src.VehicleOperations;

public class ViewRentHistory {
    private String customerID;
    private Scanner scanner;
    private VehicleOperations vehicleManager;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public ViewRentHistory(String customerID) {
        this.customerID = customerID;
        this.scanner = new Scanner(System.in);
        this.vehicleManager = new VehicleOperations();

        displayRentHistory();
    }

    private void displayRentHistory() {
        System.out.println(ANSI_YELLOW + "\n===== Rental History =====" + ANSI_RESET);

        // Get all customer rentals
        ArrayList<Rental> rentals = vehicleManager.getCustomerRentals(customerID);

        if (rentals.isEmpty()) {
            System.out.println(ANSI_RED + "You don't have any rental history." + ANSI_RESET);
            return;
        }

        // Display rental history
        System.out.println(ANSI_GREEN + "\nYour Rental History:" + ANSI_RESET);
        System.out.println("-------------------");

        for (int i = 0; i < rentals.size(); i++) {
            Rental rental = rentals.get(i);
            Vehicle vehicle = vehicleManager.getVehicleById(rental.getCarID());

            System.out.println(ANSI_YELLOW + "\nRental #" + (i + 1) + ANSI_RESET);
            System.out.println("---------");
            System.out.println(ANSI_BLUE + "Rental ID: " + rental.getRentalID() + ANSI_RESET);

            if (vehicle != null) {
                System.out.println(
                        ANSI_BLUE + "Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() + " ("
                                + vehicle.getYear() + ")" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Rental Rate: $" + vehicle.getRentalRate() + " per day" + ANSI_RESET);
                double totalCost = rental.calculateTotalCost(vehicle.getRentalRate());
                System.out.println(ANSI_BLUE + "Total Cost: $" + totalCost + ANSI_RESET);
            } else {
                System.out.println(
                        ANSI_BLUE + "Vehicle ID: " + rental.getCarID() + " (details not available)" + ANSI_RESET);
            }

            System.out.println(ANSI_BLUE + "Rental Date: " + rental.getRentalDate() + ANSI_RESET);
            System.out.println(ANSI_BLUE + "Rental Duration: " + rental.getDays() + " days" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "Status: " + (rental.isReturned() ? "Returned" : "Active") + ANSI_RESET);

            if (rental.isReturned()) {
                System.out.println(ANSI_BLUE + "Return Date: " + rental.getReturnDate() + ANSI_RESET);
            }
        }

        // Wait for user to press Enter to continue
        System.out.print(ANSI_YELLOW + "\nPress Enter to continue..." + ANSI_RESET);
        scanner.nextLine();
        new FinalChoice();
    }
}
