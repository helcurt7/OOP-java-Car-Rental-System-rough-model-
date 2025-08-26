package src;

import java.io.*;
import java.util.*;

public class VehicleOperations {
    private static final String VEHICLES_DIR = "vehicles";
    private static final String RENTALS_DIR  = "rentals";

    public VehicleOperations(){
        // Create necessary directories if they don't exist
        File vehiclesDir = new File(VEHICLES_DIR);
        if(!vehiclesDir.exists()){
            vehiclesDir.mkdir();
        }

        File rentalsDir = new File(RENTALS_DIR);
        if(!rentalsDir.exists()){
            rentalsDir.mkdir();
        }
        // Initialize the nextCarID based on existing files
        initializeNextCarID();
    }

    //Add a new vehicle to the system
    public boolean addVehicle(Vehicle vehicle){
        String fileName = VEHICLES_DIR + "/" + vehicle.getCarID() + ".txt";
        try(PrintWriter writer = new PrintWriter(new FileWriter((fileName)))){
            writer.println("CarID:" + vehicle.getCarID());
            writer.println("Brand:" + vehicle.getBrand());
            writer.println("Model:" + vehicle.getModel());
            writer.println("Year:" + vehicle.getYear());
            writer.println("RentalRate:" + vehicle.getRentalRate());
            writer.println("Available:true");
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    // Get all available vahicles
 // In VehicleOperations.java
public ArrayList<Vehicle> getAvailableVehicles() {
    ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    
    File vehiclesDir = new File(VEHICLES_DIR);
    if (!vehiclesDir.exists() || !vehiclesDir.isDirectory()) {
        return availableVehicles;
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
                boolean available = false;
                
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
                            case "Available":
                                available = Boolean.parseBoolean(value);
                                break;
                        }
                    }
                }
                
                if (available) {
                    // Create the vehicle with the exact ID from the file
                    Vehicle vehicle = new Vehicle(carID, brand, model, year, rentalRate);
                    availableVehicles.add(vehicle);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    return availableVehicles;
}

// Add this to VehicleOperations class
public void initializeNextCarID() {
    int highestID = 999;  // Start with one less than the initial nextCarID
    
    File vehiclesDir = new File(VEHICLES_DIR);
    if (!vehiclesDir.exists() || !vehiclesDir.isDirectory()) {
        return;
    }
    
    File[] files = vehiclesDir.listFiles((dir, name) -> name.endsWith(".txt"));
    if (files != null) {
        for (File file : files) {
            try {
                // Extract carID from filename
                String filename = file.getName();
                String idStr = filename.substring(0, filename.lastIndexOf("."));
                int id = Integer.parseInt(idStr);
                if (id > highestID) {
                    highestID = id;
                }
            } catch (Exception e) {
                // Skip files that don't follow the expected naming pattern
            }
        }
    }
    
    // Set the next car ID to be one more than the highest existing ID
    Vehicle.setNextCarID(highestID + 1);
}

     // Update vehicle availability
     public boolean updateVehicleAvailability(int carID, boolean available) {
        String fileName = VEHICLES_DIR + "/" + carID + ".txt";
        File vehicleFile = new File(fileName);
        
        if (!vehicleFile.exists()) {
            return false;
        }
        
        try {
            // Read the current vehicle data
            List<String> fileLines = new ArrayList<>();
            Scanner scanner = new Scanner(vehicleFile);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Available:")) {
                    line = "Available:" + available;
                }
                fileLines.add(line);
            }
            scanner.close();
            
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

     // Create a rental record
     private static int nextRentalID = 1001;
     public boolean createRentalRecord(String customerID, int carID, String rentalDate, int days) {
        // Generate a unique rental ID
        String rentalID = "R" + System.currentTimeMillis();
        String fileName = RENTALS_DIR + "/" + rentalID + ".txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("RentalID:" + rentalID);
            writer.println("CustomerID:" + customerID);
            writer.println("CarID:" + carID);
            writer.println("RentalDate:" + rentalDate);
            writer.println("Days:" + days);
            writer.println("Returned:false");
            
            // Update vehicle availability
            updateVehicleAvailability(carID, false);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Return a rented vehicle
    public boolean returnVehicle(String rentalID, String returnDate) {
        String fileName = RENTALS_DIR + "/" + rentalID + ".txt";
        File rentalFile = new File(fileName);
        
        if (!rentalFile.exists()) {
            return false;
        }
        
        try {
            // Read the current rental data
            List<String> fileLines = new ArrayList<>();
            int carID = 0;
            
            Scanner scanner = new Scanner(rentalFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Returned:")) {
                    line = "Returned:true";
                } else if (line.startsWith("CarID:")) {
                    carID = Integer.parseInt(line.split(":", 2)[1]);
                }
                fileLines.add(line);
            }
            fileLines.add("ReturnDate:" + returnDate);
            scanner.close();
            
            // Write the updated data back to the file
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            for (String line : fileLines) {
                writer.println(line);
            }
            writer.close();
            
            // Update vehicle availability
            if (carID > 0) {
                updateVehicleAvailability(carID, true);
            }
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

        // Get customer rental history
        public ArrayList<Rental> getCustomerRentals(String customerID) {
            ArrayList<Rental> rentals = new ArrayList<>();
            
            File rentalsDir = new File(RENTALS_DIR);
            if (!rentalsDir.exists() || !rentalsDir.isDirectory()) {
                return rentals;
            }
            
            File[] files = rentalsDir.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (File file : files) {
                    try (Scanner scanner = new Scanner(file)) {
                        String rentalID = "";
                        String custID = "";
                        int carID = 0;
                        String rentalDate = "";
                        int days = 0;
                        boolean returned = false;
                        String returnDate = "";
                        
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            String[] parts = line.split(":", 2);
                            
                            if (parts.length == 2) {
                                String key = parts[0];
                                String value = parts[1];
                                
                                switch (key) {
                                    case "RentalID":
                                        rentalID = value;
                                        break;
                                    case "CustomerID":
                                        custID = value;
                                        break;
                                    case "CarID":
                                        carID = Integer.parseInt(value);
                                        break;
                                    case "RentalDate":
                                        rentalDate = value;
                                        break;
                                    case "Days":
                                        days = Integer.parseInt(value);
                                        break;
                                    case "Returned":
                                        returned = Boolean.parseBoolean(value);
                                        break;
                                    case "ReturnDate":
                                        returnDate = value;
                                        break;
                                }
                            }
                        }
                        
                        if (custID.equals(customerID)) {
                            Rental rental = new Rental(rentalID, custID, carID, rentalDate, days, returned, returnDate);
                            rentals.add(rental);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            return rentals;
        }

    // Get all active rentals (not returned)
    public ArrayList<Rental> getActiveRentals(String customerID) {
        ArrayList<Rental> rentals = getCustomerRentals(customerID);
        ArrayList<Rental> activeRentals = new ArrayList<>();
        
        for (Rental rental : rentals) {
            if (!rental.isReturned()) {
                activeRentals.add(rental);
            }
        }
        
        return activeRentals;
    }

    // Helper method to get vehicle details
    public Vehicle getVehicleById(int carID) {
        String fileName = VEHICLES_DIR + "/" + carID + ".txt";
        File vehicleFile = new File(fileName);
        
        if (!vehicleFile.exists()) {
            return null;
        }
        
        try (Scanner scanner = new Scanner(vehicleFile)) {
            int id = 0;
            String brand = "";
            String model = "";
            int year = 0;
            double rentalRate = 0.0;
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":", 2);
                
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    
                    switch (key) {
                        case "CarID":
                            id = Integer.parseInt(value);
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
            
            return new Vehicle(id, brand, model, year, rentalRate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
