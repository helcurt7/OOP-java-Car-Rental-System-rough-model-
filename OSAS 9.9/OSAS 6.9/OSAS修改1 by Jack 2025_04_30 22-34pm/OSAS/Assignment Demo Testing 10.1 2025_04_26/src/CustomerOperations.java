package src;

import java.io.*;
import java.util.Scanner;

public class CustomerOperations {

    private File customersDirectory;
    private static final String CUSTOMERS_DIR = "customers";

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public CustomerOperations() {
        // Make sure the directory exists
        customersDirectory = new File(CUSTOMERS_DIR);
        if (!customersDirectory.exists()) {
            customersDirectory.mkdir();
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

    // Check if ID is available (not taken)
    public boolean isIDAvailable(String ID) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        return !customerFile.exists();
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        writeCustomerToFile(customer);
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

    // Get full profile info of a customer
    public String getProfileInfo(String ID) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        if (!customerFile.exists()) {
            return RED + "No profile found for ID: " + ID + RESET;
        }

        Customer customer = readCustomerFromFile(customerFile);
        if (customer == null) {
            return RED + "Error reading profile for ID: " + ID + RESET;
        }

        return customer.toString();
    }

    // Change password
    public boolean changePassword(String ID, String password) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        if (!customerFile.exists()) {
            System.out.println(RED + "Customer file not found for ID: " + ID + RESET);
            return false;
        }

        Customer customer = readCustomerFromFile(customerFile);
        if (customer == null) {
            System.out.println(RED + "Error reading customer data from file for ID: " + ID + RESET);
            return false;
        }

        customer.setPassword(password);
        writeCustomerToFile(customer);
        System.out.println(GREEN + "Password changed successfully for ID: " + ID + RESET);
        return true;
    }

    // Update email
    public boolean updateEmail(String ID, String email) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        if (!customerFile.exists()) {
            System.out.println(RED + "Customer file not found for ID: " + ID + RESET);
            return false;
        }

        Customer customer = readCustomerFromFile(customerFile);
        if (customer == null) {
            System.out.println(RED + "Error reading customer data from file for ID: " + ID + RESET);
            return false;
        }

        customer.setEmail(email);
        writeCustomerToFile(customer);
        System.out.println(GREEN + "Email updated successfully for ID: " + ID + RESET);
        return true;
    }

    // Update phone number
    public boolean updatePhoneNumber(String ID, String phoneNo) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        if (!customerFile.exists()) {
            System.out.println(RED + "Customer file not found for ID: " + ID + RESET);
            return false;
        }

        Customer customer = readCustomerFromFile(customerFile);
        if (customer == null) {
            System.out.println(RED + "Error reading customer data from file for ID: " + ID + RESET);
            return false;
        }

        customer.setPhoneNumber(phoneNo);
        writeCustomerToFile(customer);
        System.out.println(GREEN + "Phone number updated successfully for ID: " + ID + RESET);
        return true;
    }

    // Check if credentials match
    public boolean credentialsFound(String ID, String password) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        if (!customerFile.exists()) {
            System.out.println(RED + "Customer file not found for ID: " + ID + RESET);
            return false;
        }

        Customer customer = readCustomerFromFile(customerFile);
        if (customer == null) {
            System.out.println(RED + "Error reading customer data from file for ID: " + ID + RESET);
            return false;
        }

        boolean match = password.equals(customer.getPassword()); // <-- case-sensitive password match
        if (!match) {
            System.out.println(RED + "Invalid credentials for ID: " + ID + RESET);
        }
        return match;
    }

    // Check if ID exists
    public boolean IDExists(String ID) {
        File customerFile = new File(CUSTOMERS_DIR + "/" + ID + ".txt");
        boolean exists = customerFile.exists();
        if (!exists) {
            System.out.println(RED + "ID does not exist: " + ID + RESET);
        }
        return exists;
    }

    // Check if IC exists
    public boolean ICExists(String ic) {
        File[] customerFiles = customersDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (customerFiles != null) {
            for (File file : customerFiles) {
                Customer customer = readCustomerFromFile(file);
                if (customer != null && customer.getIC().equals(ic)) {
                    System.out.println(RED + "IC already exists: " + ic + RESET);
                    return true;
                }
            }
        }
        return false;
    }

    // Check if license number exists
    public boolean licenseNumberExists(String licenseNumber) {
        File[] customerFiles = customersDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (customerFiles != null) {
            for (File file : customerFiles) {
                Customer customer = readCustomerFromFile(file);
                if (customer != null && customer.getLicenseNumber().equals(licenseNumber)) {
                    System.out.println(RED + "License number already exists: " + licenseNumber + RESET);
                    return true;
                }
            }
        }
        return false;
    }
}
