package src.Contoller;

import java.util.ArrayList;
import java.util.Scanner;
import src.Vehicle;
import src.VehicleOperations;

public class ViewAvailableVehicles {
    private Scanner scanner;
    private VehicleOperations vehicleOperations;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public ViewAvailableVehicles() {
        scanner = new Scanner(System.in);
        vehicleOperations = new VehicleOperations();
        displayAvailableVehicles();
    }

    private void displayAvailableVehicles() {
        System.out.println(ANSI_YELLOW + "\n===== Available Vehicles =====" + ANSI_RESET);
        ArrayList<Vehicle> availableVehicles = vehicleOperations.getAvailableVehicles();

        if (availableVehicles.isEmpty()) {
            System.out.println(ANSI_RED + "No vehicles are currently available for rent." + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "The following vehicles are available for rent:" + ANSI_RESET);
            System.out.println("------------------------------------------------------");
            System.out.printf("%-8s | %-10s | %-15s | %-6s | %s\n",
                    "Car ID", "Brand", "Model", "Year", "Rate/Day");
            System.out.println("------------------------------------------------------");

            for (Vehicle vehicle : availableVehicles) {
                System.out.printf("%-8d | %-10s | %-15s | %-6d | $%.2f\n",
                        ANSI_BLUE + vehicle.getCarID() + ANSI_RESET,
                        ANSI_BLUE + vehicle.getBrand() + ANSI_RESET,
                        ANSI_BLUE + vehicle.getModel() + ANSI_RESET,
                        ANSI_BLUE + vehicle.getYear() + ANSI_RESET,
                        ANSI_BLUE + vehicle.getRentalRate() + ANSI_RESET);
            }
            System.out.println("------------------------------------------------------");
        }

        new FinalChoice();
    }
}
