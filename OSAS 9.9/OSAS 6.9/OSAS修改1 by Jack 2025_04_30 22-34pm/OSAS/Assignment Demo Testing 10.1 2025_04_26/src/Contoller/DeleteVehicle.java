package src.Contoller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import src.Vehicle;

public class DeleteVehicle {
    private Scanner scanner;
    private static final String VEHICLES_DIR = "vehicles";
    private static final String RENTALS_DIR = "rentals";

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public DeleteVehicle() {
        this.scanner = new Scanner(System.in);
        processDeleteVehicle();
    }

    private void processDeleteVehicle() {
        System.out.println(ANSI_YELLOW + "\n===== Delete Vehicle =====" + ANSI_RESET);

        // Display available vehicles
        ArrayList<Vehicle> vehicles = getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println(ANSI_RED + "No vehicles found in the system." + ANSI_RESET);
            return;
        }

        System.out.println("\nVehicles in the system:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }

        // Get vehicle ID to delete
        System.out.print("\nEnter the Car ID to delete: ");
        int carID = getIntInput();

        // Check if vehicle exists
        boolean vehicleExists = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCarID() == carID) {
                vehicleExists = true;
                break;
            }
        }

        if (!vehicleExists) {
            System.out.println(ANSI_RED + "Vehicle with ID " + carID + " not found." + ANSI_RESET);
            return;
        }

        // Check if the vehicle is currently rented
        if (isVehicleRented(carID)) {
            System.out.println(ANSI_RED + "Cannot delete vehicle as it is currently rented." + ANSI_RESET);
            return;
        }

        // Confirm deletion
        System.out.print("Are you sure you want to delete this vehicle? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y") || confirmation.equals("yes")) {
            boolean success = deleteVehicle(carID);

            if (success) {
                System.out.println(ANSI_GREEN + "\nVehicle deleted successfully!" + ANSI_RESET);
            } else {
                System.out.println(
                        ANSI_RED + "\nError occurred while deleting the vehicle. Please try again." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_YELLOW + "\nDeletion cancelled." + ANSI_RESET);
        }
    }

    private boolean deleteVehicle(int carID) {
        String fileName = VEHICLES_DIR + "/" + carID + ".txt";
        File vehicleFile = new File(fileName);

        if (!vehicleFile.exists()) {
            return false;
        }

        return vehicleFile.delete();
    }

    private boolean isVehicleRented(int carID) {
        File rentalsDir = new File(RENTALS_DIR);
        if (!rentalsDir.exists() || !rentalsDir.isDirectory()) {
            return false;
        }

        File[] files = rentalsDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    int rentalCarID = 0;
                    boolean returned = true;

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] parts = line.split(":", 2);

                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim();

                            if (key.equals("CarID")) {
                                rentalCarID = Integer.parseInt(value);
                            } else if (key.equals("Returned")) {
                                returned = Boolean.parseBoolean(value);
                            }
                        }
                    }

                    if (rentalCarID == carID && !returned) {
                        return true; // Vehicle is currently rented
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return false; // Vehicle is not currently rented
    }

    private ArrayList<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        File vehiclesDir = new File(VEHICLES_DIR);
        if (!vehiclesDir.exists() || !vehiclesDir.isDirectory()) {
            return vehicles;
        }

        File[] files = vehiclesDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    int carID = 0;
                    String brand = "";
                    String model = "";
                    int year = 0;
                    double rentalRate = 0.0;

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] parts = line.split(":", 2);

                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim();

                            switch (key) {
                                case "CarID":
                                    carID = Integer.parseInt(value);
                                    break;
                                case "Brand":
                                    brand = value;
                                    break;
                                case "Model":
                                    model = value;
                                    break;
                                case "Year":
                                    year = Integer.parseInt(value);
                                    break;
                                case "RentalRate":
                                    rentalRate = Double.parseDouble(value);
                                    break;
                            }
                        }
                    }

                    if (carID > 0) {
                        Vehicle vehicle = new Vehicle(carID, brand, model, year, rentalRate);
                        vehicles.add(vehicle);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return vehicles;
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
