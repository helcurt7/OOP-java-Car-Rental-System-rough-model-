package src.Contoller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import src.Vehicle;

public class UpdateVehicleCost {
    private Scanner scanner;
    private static final String VEHICLES_DIR = "vehicles";

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public UpdateVehicleCost() {
        this.scanner = new Scanner(System.in);
        processUpdateVehicleCost();
    }

    private void processUpdateVehicleCost() {
        System.out.println(ANSI_YELLOW + "\n===== Update Vehicle Rental Rate =====" + ANSI_RESET);

        // Display available vehicles
        ArrayList<Vehicle> availableVehicles = getAllVehicles();
        if (availableVehicles.isEmpty()) {
            System.out.println(ANSI_RED + "No vehicles found in the system." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_GREEN + "\nAvailable Vehicles:" + ANSI_RESET);
        for (Vehicle vehicle : availableVehicles) {
            System.out.println(ANSI_BLUE + vehicle.toString() + ANSI_RESET);
        }

        // Get vehicle ID to update
        System.out.print(ANSI_YELLOW + "\nEnter the Car ID to update: " + ANSI_RESET);
        int carID = getIntInput();

        // Find the vehicle
        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : availableVehicles) {
            if (vehicle.getCarID() == carID) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle == null) {
            System.out.println(ANSI_RED + "Vehicle with ID " + carID + " not found." + ANSI_RESET);
            return;
        }

        // Get new rental rate
        System.out.println(
                ANSI_GREEN + "Current rental rate: $" + selectedVehicle.getRentalRate() + " per day" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + "Enter new rental rate: $" + ANSI_RESET);
        double newRate = getDoubleInput();

        // Update the vehicle rental rate
        boolean success = updateVehicleRate(carID, newRate);

        if (success) {
            System.out.println(ANSI_GREEN + "\nVehicle rental rate updated successfully!" + ANSI_RESET);
        } else {
            System.out.println(
                    ANSI_RED + "\nError occurred while updating the rental rate. Please try again." + ANSI_RESET);
        }
    }

    private boolean updateVehicleRate(int carID, double newRate) {
        String fileName = VEHICLES_DIR + "/" + carID + ".txt";
        File vehicleFile = new File(fileName);

        if (!vehicleFile.exists()) {
            return false;
        }

        try {
            // Read the current vehicle data
            ArrayList<String> fileLines = new ArrayList<>();
            Scanner fileScanner = new Scanner(vehicleFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith("RentalRate:")) {
                    line = "RentalRate:" + newRate;
                }
                fileLines.add(line);
            }
            fileScanner.close();

            // Write the updated data back to the file
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            for (String line : fileLines) {
                writer.println(line);
            }
            writer.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
            System.out.print(ANSI_YELLOW + "Please enter a valid number: " + ANSI_RESET);
            return getIntInput();
        }
    }

    private double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print(ANSI_YELLOW + "Please enter a valid number: " + ANSI_RESET);
            return getDoubleInput();
        }
    }
}
