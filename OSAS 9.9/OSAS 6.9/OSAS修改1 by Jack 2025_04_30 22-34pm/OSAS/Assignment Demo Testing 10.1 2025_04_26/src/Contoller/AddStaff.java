package src.Contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import src.Admin;
import src.Main;
import src.Staff;

public class AddStaff {

    private Scanner scanner;
    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public AddStaff() {
        scanner = new Scanner(System.in);
        addNewStaff();
    }

    // Check if staff ID already exists
    private boolean staffIDExists(String staffID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming staff ID is at the beginning of each line
                if (line.contains("Staff ID: " + staffID)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // If file doesn't exist, ID doesn't exist
            return false;
        }
        return false;
    }

    // Check if license number already exists
    private boolean licenseNumberExists(String licenseNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("License Number: " + licenseNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    // Check if IC already exists
    // Check if IC already exists (for multi-line format)
    private boolean ICExists(String icToCheck) {
        try (BufferedReader reader = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Remove leading/trailing whitespace
                if (line.startsWith("IC")) { // Check if line starts with "IC"
                    String[] parts = line.split(":", 2); // Split into key and value
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        if (key.equals("IC") && value.equals(icToCheck)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public void addNewStaff() {
        System.out.println("\n===== Add New Staff =====");
        System.out.println("(Type \"Q\" at any prompt to cancel and return to Admin menu)");

        // Create a new Staff object with default constructor
        Staff newStaff = new Staff();

        // ------------- Get Staff ID -------------
        String staffID;
        do {
            System.out.print("\nPlease enter Staff ID (0 to go back to Main Menu, Q to return to Admin Menu): ");
            staffID = scanner.nextLine();

            // Check for Q exit option
            if (staffID.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (staffID.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (staffID.isEmpty()) {
                System.out.println(ANSI_RED + " \nID cannot be empty! Please try again! " + ANSI_RESET);
                System.out.println("\nFor Example: STAFF001, ADMIN6954...");
                continue;
            }

            if (staffID.length() >= 20) {
                System.out.println(ANSI_RED + "ID cannot be more than or equal to 20 characters!" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please try again!" + ANSI_RESET);
                continue;
            }

            if (staffIDExists(staffID)) {
                System.out.println(ANSI_RED + "\n\t\t\tID already exists! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\t\t  Please choose a different ID! " + ANSI_RESET);
                staffID = "";
            }
        } while (staffID.isEmpty());
        newStaff.setStaffID(staffID);

        // ------------- First Name: Ensure only alphabetic characters -------------
        String firstName;
        do {
            System.out.print(
                    "\nEnter First Name (alphabetic characters only) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            firstName = scanner.nextLine();

            // Check for Q exit option
            if (firstName.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (firstName.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (firstName.isEmpty()) {
                System.out.println(ANSI_RED + "\nFirst Name cannot be empty!\n" + ANSI_RESET);
                continue;
            }

            if (!firstName.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "\nFirst Name must contain only alphabetic characters!\n" + ANSI_RESET);
                firstName = "";
            }
        } while (firstName.isEmpty());
        newStaff.setFirstName(firstName);
        System.out.println(ANSI_GREEN + "First Name created successfully!\n" + ANSI_RESET);

        // ------------- Last Name: Ensure only alphabetic characters -------------
        String lastName;
        do {
            System.out.print(
                    "Enter Last Name (alphabetic characters only) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            lastName = scanner.nextLine();

            // Check for Q exit option
            if (lastName.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (lastName.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (lastName.isEmpty()) {
                System.out.println(ANSI_RED + "\nLast Name cannot be empty!\n" + ANSI_RESET);
                continue;
            }

            if (!lastName.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "\nLast Name must contain only alphabetic characters!\n" + ANSI_RESET);
                lastName = "";
            }
        } while (lastName.isEmpty());
        newStaff.setLastName(lastName);
        System.out.println(ANSI_GREEN + "Last Name created successfully!\n" + ANSI_RESET);

        // ------------------------------------- License Number
        // ----------------------------------------------------------
        String licenseNumber;
        do {
            System.out.print(
                    "Enter your License Number (Example: ABC1234D) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            licenseNumber = scanner.nextLine();

            // Check for Q exit option
            if (licenseNumber.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (licenseNumber.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (licenseNumber.isEmpty()) {
                System.out.println(ANSI_RED + "License Number cannot be empty !\n" + ANSI_RESET);
                continue;
            }

            // Example validation: Assuming license number has a specific format (e.g., 3
            // alphabetic followed by 4 digits followed by 1 alphabetic)
            if (!licenseNumber.matches("[a-zA-Z]{3}\\d{4}[a-zA-Z]{1}")) {
                System.out.println(
                        ANSI_RED + "Invalid License Number format! Please use the format: AAA1234B (3 letters, 4 numbers, 1 letter)."
                                + ANSI_RESET);
                licenseNumber = "";
                continue;
            }

            if (licenseNumberExists(licenseNumber)) {
                System.out.println(ANSI_RED + "\n\t\tLicense Number already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\t\t Please enter a different License Number ! " + ANSI_RESET);
                licenseNumber = "";
            }
        } while (licenseNumber.isEmpty());
        newStaff.setLicenseNumber(licenseNumber);
        System.out.println(ANSI_GREEN + "License Number is created successfully !\n" + ANSI_RESET);

        // ------------- Phone Number: Exactly 11 digits -------------
        String phoneNumber;
        do {
            System.out.print("Enter Phone Number (11 digits) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            phoneNumber = scanner.nextLine();

            // Check for Q exit option
            if (phoneNumber.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (phoneNumber.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (phoneNumber.isEmpty()) {
                System.out.println(ANSI_RED + "Phone Number cannot be empty!" + ANSI_RESET);
                continue;
            }

            if (!phoneNumber.matches("\\d+")) {
                System.out.println(ANSI_RED + "Phone Number must contain only digits!" + ANSI_RESET);
                phoneNumber = "";
                continue;
            }

            if (phoneNumber.length() != 11) {
                System.out.println(ANSI_RED + "Phone Number must be exactly 11 digits!" + ANSI_RESET);
                phoneNumber = "";
            }
        } while (phoneNumber.isEmpty());
        newStaff.setPhoneNumber(phoneNumber);
        System.out.println(ANSI_GREEN + "Phone Number created successfully!\n" + ANSI_RESET);

        // ------------- Email: Must include '@' and 'mail.com' -------------
        String email;
        do {
            System.out.print(
                    "Enter Email (must include @ and mail.com) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            email = scanner.nextLine();

            // Check for Q exit option
            if (email.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (email.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (email.isEmpty()) {
                System.out.println(ANSI_RED + "Email cannot be empty!" + ANSI_RESET);
                continue;
            }

            if (!email.contains("@") || !email.endsWith("mail.com")) {
                System.out.println(ANSI_RED + "\nEmail must contain '@' and end with 'mail.com'!" + ANSI_RESET);
                System.out.println(ANSI_RED + "\nFor example: staff@mail.com" + ANSI_RESET);
                email = "";
            }
        } while (email.isEmpty());
        newStaff.setEmail(email);
        System.out.println(ANSI_GREEN + "Email created successfully!\n" + ANSI_RESET);

        // ------------- IC Number: Exactly 12 digits -------------
        String ic;
        do {
            System.out.print(
                    "Please enter Staff IC (12 digits) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            ic = scanner.nextLine();

            // Check for Q exit option
            if (ic.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (ic.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (ic.isEmpty()) {
                System.out.println(ANSI_RED + "IC cannot be empty! \n" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please try again !" + ANSI_RESET);
                continue;
            }
            if (!ic.matches("\\d+")) {
                System.out.println(ANSI_RED + "IC must contain only digits !\n" + ANSI_RESET);
                ic = "";
                continue;
            }
            if (ic.length() != 12) {
                System.out.println(ANSI_RED + "\nIC must be exactly 12 digits!\n" + ANSI_RESET);
                System.out.println(ANSI_RED + "An example would be 850502101234\n" + ANSI_RESET);
                ic = "";
                continue;
            }
            if (ICExists(ic)) {
                System.out.println(ANSI_RED + "\tIC already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\tPlease choose a different IC ! " + ANSI_RESET);
                ic = "";
            }
        } while (ic.isEmpty());
        newStaff.setIC(ic);
        System.out.println(ANSI_GREEN + "IC Number created successfully !\n" + ANSI_RESET);

        // ------------- Position: Ensure only alphabetic characters -------------
        String position;
        do {
            System.out.print(
                    "Enter Position (alphabetic characters only) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            position = scanner.nextLine();

            // Check for Q exit option
            if (position.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (position.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (position.isEmpty()) {
                System.out.println(ANSI_RED + "\nPosition cannot be empty!\n" + ANSI_RESET);
                continue;
            }

            if (!position.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "\nPosition must contain only alphabetic characters!\n" + ANSI_RESET);
                position = "";
            }
        } while (position.isEmpty());
        newStaff.setPosition(position);
        System.out.println(ANSI_GREEN + "Position created successfully!\n" + ANSI_RESET);

        // ------------- Salary: Must be a positive number -------------
        double salary;
        while (true) {
            System.out.print(
                    "Enter Salary (must be a positive number) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            String salaryInput = scanner.nextLine();

            // Check for Q exit option
            if (salaryInput.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling staff creation. Returning to admin menu...\n");
                new Admin();
                return;
            }

            if (salaryInput.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new Main();
                return;
            }

            if (salaryInput.isEmpty()) {
                System.out.println(ANSI_RED + "Salary cannot be empty!" + ANSI_RESET);
                continue;
            }

            try {
                salary = Double.parseDouble(salaryInput);
                if (salary <= 0) {
                    System.out.println(ANSI_RED + "Salary must be a positive number!" + ANSI_RESET);
                    continue;
                }
                break; // Break the loop if valid salary
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input! Please enter a valid number for salary." + ANSI_RESET);
            }
        }
        newStaff.setSalary(salary);
        System.out.println(ANSI_GREEN + "Salary created successfully!\n" + ANSI_RESET);

        // Set all properties for the Staff object
        newStaff = new Staff(firstName, lastName, licenseNumber, phoneNumber, email, ic, staffID, position, salary);

        // Save to file
        try {
            // Make sure the file exists
            File file = new File("staff.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            // Append to the file
            FileWriter writer = new FileWriter("staff.txt", true);
            writer.write(newStaff.toString() + "\n");
            writer.close();

            System.out.println(ANSI_GREEN + "\n== Staff profile added and saved successfully ==\n" + ANSI_RESET);

            System.out.print("\nPress Enter to return to Admin menu or Q to quit: ");
            String finalChoice = scanner.nextLine();
            if (finalChoice.equalsIgnoreCase("Q")) {
                System.out.println("Exiting program...");
                System.exit(0);
            } else {
                new Admin();
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "❌ Error writing to staff.txt: " + e.getMessage() + ANSI_RESET);
            System.out.println("\nReturning to admin menu...");
            new Admin();
        }
    }
}
