package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminOperations {

    private ArrayList<Customer> collectionCustomers;
    private File customersDirectory;
    private final String CUSTOMERS_DIR = "customers";
    private final String STAFF_DIR = "staff";
    private final String VEHICLES_DIR = "vehicles";

    public AdminOperations() {
        collectionCustomers = new ArrayList<Customer>();
        customersDirectory = new File(CUSTOMERS_DIR);
        if (!customersDirectory.exists()) {
            customersDirectory.mkdir();
        }

        // Create staff directory if it doesn't exist
        File staffDirectory = new File(STAFF_DIR);
        if (!staffDirectory.exists()) {
            staffDirectory.mkdir();
        }

        // Create vehicles directory if it doesn't exist
        File vehiclesDirectory = new File(VEHICLES_DIR);
        if (!vehiclesDirectory.exists()) {
            vehiclesDirectory.mkdir();
        }
    }

    // Helper method to write a customer to a file
    private void writeCustomerToFile(Customer customer) {
        String fileName = CUSTOMERS_DIR + "/" + customer.getID() + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("ID:" + customer.getID());
            writer.println("Password:" + customer.getPassword());
            writer.println("FirstName:" + customer.getFirstName());
            writer.println("LastName:" + customer.getLastName());
            writer.println("License Number:" + customer.getLicenseNumber());
            writer.println("Email:" + customer.getEmail());
            writer.println("PhoneNumber:" + customer.getPhoneNumber());
            writer.println("IC:" + customer.getIC());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to read a customer from a file
    private Customer readCustomerFromFile(File file) {
        Customer customer = new Customer();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":", 2);

                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];

                    switch (key) {
                        case "ID":
                            customer.setID(value);
                            break;
                        case "Password":
                            customer.setPassword(value);
                            break;
                        case "FirstName":
                            customer.setFirstName(value);
                            break;
                        case "LastName":
                            customer.setLastName(value);
                            break;
                        case "License Number":
                            customer.setLicenseNumber(value);
                            break;
                        case "Email":
                            customer.setEmail(value);
                            break;
                        case "PhoneNumber":
                            customer.setPhoneNumber(value);
                            break;
                        case "IC":
                            customer.setIC(value);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return customer;
    }

    // Helper method to read a staff from a file
    private Staff readStaffFromFile(File file) {
        Staff staff = new Staff();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":", 2);

                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];

                    switch (key) {
                        case "StaffID":
                            staff.setStaffID(value);
                            break;
                        case "Position":
                            staff.setPosition(value);
                            break;
                        case "Salary":
                            staff.setSalary(Double.parseDouble(value));
                            break;
                        case "FirstName":
                            staff.setFirstName(value);
                            break;
                        case "LastName":
                            staff.setLastName(value);
                            break;
                        case "License Number":
                            staff.setLicenseNumber(value);
                            break;
                        case "Email":
                            staff.setEmail(value);
                            break;
                        case "PhoneNumber":
                            staff.setPhoneNumber(value);
                            break;
                        case "IC":
                            staff.setIC(value);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Error parsing salary: " + e.getMessage());
            return null;
        }
        return staff;
    }

    // Helper method to read a vehicle from a file
    private Vehicle readVehicleFromFile(File file) {
        int carID = 0;
        String brand = "";
        String model = "";
        int year = 0;
        double rentalRate = 0.0;
        String status = "Available";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":", 2);

                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];

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
                        case "Status":
                            status = value;
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numeric value: " + e.getMessage());
            return null;
        }

        Vehicle vehicle = new Vehicle(carID, brand, model, year, rentalRate);
        return vehicle;
    }

    public void addCustomer(Customer customer) {
        writeCustomerToFile(customer);
    }

    public ArrayList<Customer> viewAllProfiles() {
        ArrayList<Customer> customers = new ArrayList<>();

        if (!customersDirectory.exists() || !customersDirectory.isDirectory()) {
            return customers;
        }

        File[] files = customersDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                Customer customer = readCustomerFromFile(file);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        }
        return customers;
    }

    public ArrayList<Staff> viewAllStaff() {
        ArrayList<Staff> staffList = new ArrayList<>();
        File staffDirectory = new File(STAFF_DIR);

        if (!staffDirectory.exists() || !staffDirectory.isDirectory()) {
            return staffList;
        }

        File[] files = staffDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                Staff staff = readStaffFromFile(file);
                if (staff != null) {
                    staffList.add(staff);
                }
            }
        }
        return staffList;
    }

    public ArrayList<Vehicle> viewAllVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        File vehiclesDirectory = new File(VEHICLES_DIR);

        if (!vehiclesDirectory.exists() || !vehiclesDirectory.isDirectory()) {
            return vehicles;
        }

        File[] files = vehiclesDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                Vehicle vehicle = readVehicleFromFile(file);
                if (vehicle != null) {
                    vehicles.add(vehicle);
                }
            }
        }
        return vehicles;
    }

    public ArrayList<Customer> searchByCustomerID(String customerID) {
        ArrayList<Customer> matchingCustomers = new ArrayList<>();

        if (!customersDirectory.exists() || !customersDirectory.isDirectory()) {
            return matchingCustomers;
        }

        File customerFile = new File(CUSTOMERS_DIR + "/" + customerID + ".txt");
        if (customerFile.exists()) {
            Customer customer = readCustomerFromFile(customerFile);
            if (customer != null) {
                matchingCustomers.add(customer);
            }
        }
        return matchingCustomers;
    }

    public boolean removeCustomer(String ID) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        if (!customerFile.exists()) {
            return false;
        }
        return customerFile.delete();
    }

    public boolean IDExists(String ID) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        return customerFile.exists();
    }

    public boolean licenseNumberExists(String licenseNumber) {
        if (!customersDirectory.exists() || !customersDirectory.isDirectory()) {
            return false;
        }

        File[] files = customersDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.startsWith("License Number:" + licenseNumber)) {
                            return true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    // Handle exception if a file is unexpectedly not found
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean ICExists(String ic) {
        if (!customersDirectory.exists() || !customersDirectory.isDirectory()) {
            return false;
        }

        File[] files = customersDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.startsWith("IC:" + ic)) {
                            return true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    // Handle exception if a file is unexpectedly not found
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
