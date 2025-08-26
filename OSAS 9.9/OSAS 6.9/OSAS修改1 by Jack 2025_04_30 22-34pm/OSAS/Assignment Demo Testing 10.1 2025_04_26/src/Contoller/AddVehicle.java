package src.Contoller;

import java.util.Scanner;
import src.Admin;
import src.Vehicle;
import src.VehicleOperations;

public class AddVehicle {
    private Scanner scanner;
    private VehicleOperations vehicleManager;
    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public AddVehicle() {
        this.scanner = new Scanner(System.in);
        this.vehicleManager = new VehicleOperations();

        processAddVehicle();
    }

    private void processAddVehicle() {
        System.out.println("\n===== Add New Vehicle ===== (Type \"Q\" to exit at any prompt)");

        String brand = getNonEmptyString("Enter vehicle brand: ");
        if (brand == null)
            returnToMainMenu();

        String model = getNonEmptyString("Enter vehicle model: ");
        if (model == null)
            returnToMainMenu();

        Integer year = getIntInput("Enter vehicle year: ", 2010, 2025);
        if (year == null)
            returnToMainMenu();

        Double rentalRate = getDoubleInput("Enter daily rental rate ($): ", 0, 10000);
        if (rentalRate == null)
            returnToMainMenu();

        Vehicle vehicle = new Vehicle(0, brand, model, year, rentalRate);
        boolean success = vehicleManager.addVehicle(vehicle);

        if (success) {
            System.out.println(ANSI_GREEN + "\nVehicle added successfully!" + ANSI_RESET);
            System.out.println("Vehicle ID: " + vehicle.getCarID());
        } else {
            System.out.println(ANSI_RED + "\nError occurred while adding the vehicle. Please try again." + ANSI_RESET);
        }
    }

    private String getNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Q"))
                return null;
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println(ANSI_RED + "Input cannot be empty. Please try again." + ANSI_RESET);
            }
        }
    }

    private Integer getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Q"))
                return null;
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(
                            ANSI_RED + "Please enter a value between " + min + " and " + max + "." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
            }
        }
    }

    private Double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Q"))
                return null;
            try {
                double value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(
                            ANSI_RED + "Please enter a value between " + min + " and " + max + "." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
            }
        }
    }

    private void returnToMainMenu() {
        System.out.print("\nPress Enter to return to Admin menu...");
        scanner.nextLine();
        new Admin();
    }
}
