package src.Contoller;

import java.util.Scanner;
import src.Admin;
import src.AdminOperations;
import src.Customer;
import src.Main;

public class AddCustomer {

    private Scanner scanner;
    private AdminOperations adminOps;

    // ANSI escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public AddCustomer() {
        scanner = new Scanner(System.in);
        adminOps = new AdminOperations();
        addNewCustomer();
    }

    public void addNewCustomer() {
        System.out.println("\n===== Add New Customer =====");
        System.out.println("(Type \"Q\" at any prompt to cancel and return to Admin menu)");

        // Create a new Customer object
        Customer newCustomer = new Customer();

        // ------------- Get customer ID -------------
        String id;
        do {
            System.out.print("\nPlease enter Customer ID (0 to go back to Main Menu, Q to return to Admin Menu): ");
            id = scanner.nextLine();

            // Check for Q exit option
            if (id.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (id.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new WaitingTime();
                new Main();
                return;
            }

            if (id.isEmpty()) {
                System.out.println(ANSI_RED + " \nID cannot be empty! Please try again ! " + ANSI_RESET);
                System.out.println("\nFor Example : Daddy001, Delay No More6954 ... ");
                continue;
            }

            if (id.length() >= 20) {
                System.out.println(ANSI_RED + "ID cannot be more than or equal to 20 characters!" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please try again!" + ANSI_RESET);
                continue;
            }

            if (adminOps.IDExists(id)) {
                System.out.println(ANSI_RED + "\n\t\t\tID already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\t\t  Please choose a different ID ! " + ANSI_RESET);
                id = "";
            }
        } while (id.isEmpty());
        newCustomer.setID(id);

        // ------------- Get customer details -------------
        // Password no limit anything just do it what you want
        String password;
        do {
            System.out.println(
                    "\n*Take Note : Please do not set a password that is too complex, and remember it if necessary.\n");
            System.out.print("Enter Password (0 to go back to Main Menu, Q to return to Admin Menu): ");
            password = scanner.nextLine();

            // Check for Q exit option
            if (password.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (password.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new WaitingTime();
                new Main();
                return;
            }

            if (password.isEmpty()) {
                System.out.println(ANSI_RED + "Password cannot be empty ! " + ANSI_RESET);
                continue;
            }
        } while (password.isEmpty());
        newCustomer.setPassword(password);
        System.out.println(ANSI_GREEN + "Password created successfully !\n" + ANSI_RESET);

        // ------------- First/Last Name: Ensure only alphabetic characters
        // -------------
        String firstName;
        do {
            System.out.print(
                    "Enter First Name (alphabetic characters only) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            firstName = scanner.nextLine();

            // Check for Q exit option
            if (firstName.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (firstName.equals("0")) {
                System.out.println("Returning to main menu...");
                new WaitingTime();
                new Main();
                return;
            }

            if (firstName.isEmpty()) {
                System.out.println(ANSI_RED + "\nFirst Name cannot be empty !\n" + ANSI_RESET);
                continue;
            }
            if (!firstName.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "\nFirst Name must contain only alphabetic characters !\n" + ANSI_RESET);
                firstName = "";
            }
        } while (firstName.isEmpty());
        newCustomer.setFirstName(firstName);
        System.out.println(ANSI_GREEN + "First Name created successfully !\n" + ANSI_RESET);

        // ------------- Get Last Name with validation -------------
        String lastName;
        do {
            System.out.print(
                    "\nEnter Last Name (alphabetic characters only) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            lastName = scanner.nextLine();

            // Check for Q exit option
            if (lastName.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (lastName.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new WaitingTime();
                new Main();
                return;
            }

            if (lastName.isEmpty()) {
                System.out.println(ANSI_RED + "\nLast Name cannot be empty!" + ANSI_RESET);
                continue;
            }
            if (!lastName.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "Last Name must contain only alphabetic characters!" + ANSI_RESET);
                lastName = "";
            }
        } while (lastName.isEmpty());
        newCustomer.setLastName(lastName);
        System.out.println(ANSI_GREEN + "Last Name created successfully !\n" + ANSI_RESET);

        // ------------------------------------- License Number
        // ----------------------------------------------------------
        String licenseNumber;
        do {
            System.out.print(
                    "Enter your License Number (Example: ABC1234D) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            licenseNumber = scanner.nextLine();

            // Check for Q exit option
            if (licenseNumber.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (licenseNumber.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new WaitingTime();
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

            if (adminOps.licenseNumberExists(licenseNumber)) { // Assuming this method exists in AdminOperations
                System.out.println(ANSI_RED + "\n\t\tLicense Number already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\t\t Please enter a different License Number ! " + ANSI_RESET);
                licenseNumber = "";
            }
        } while (licenseNumber.isEmpty());
        newCustomer.setLicenseNumber(licenseNumber);
        System.out.println(ANSI_GREEN + "License Number is created successfully !\n" + ANSI_RESET);

        // ------------- Email: Must include '@' and 'mail.com' -------------
        String email;
        do {
            System.out.print(
                    "\nEnter Email (must include @ and mail.com) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            email = scanner.nextLine();

            // Check for Q exit option
            if (email.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (email.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new WaitingTime();
                new Main();
                return;
            }

            if (email.isEmpty()) {
                System.out.println(ANSI_RED + "Email cannot be empty!" + ANSI_RESET);
                continue;
            }
            if (!email.contains("@") || !email.endsWith("mail.com")) {
                System.out.println(ANSI_RED + "\nEmail must contain '@' and end with 'mail.com'!" + ANSI_RESET);
                System.out.println(ANSI_RED + "\nFor example : Delaynomore@mail.com" + ANSI_RESET);
                email = "";
            }
        } while (email.isEmpty());
        newCustomer.setEmail(email);
        System.out.println(ANSI_GREEN + "Email created successfully !\n" + ANSI_RESET);

        // ------------- Phone Number: Exactly 11 digits -------------
        String phoneNumber;
        do {
            System.out.print("Enter Phone Number (11 digits) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            phoneNumber = scanner.nextLine();

            // Check for Q exit option
            if (phoneNumber.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling customer creation. Returning to admin menu...\n");
                new WaitingTime();
                new Admin();
                return;
            }

            if (phoneNumber.equals("0")) {
                System.out.println("\nReturning to main menu...\n");
                new WaitingTime();
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
        newCustomer.setPhoneNumber(phoneNumber);
        System.out.println(ANSI_GREEN + "Phone Number created successfully !\n" + ANSI_RESET);

        // ------------- IC Number: Exactly 12 digits -------------
        String ic;
        do {
            System.out.print(
                    "Please enter Customer IC (12 digits) (0 to go back to Main Menu, Q to return to Admin Menu): ");
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
                new WaitingTime();
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
            if (adminOps.ICExists(ic)) {
                System.out.println(ANSI_RED + "\tIC already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\tPlease choose a different IC ! " + ANSI_RESET);
                ic = "";
            }
        } while (ic.isEmpty());
        newCustomer.setIC(ic);
        System.out.println(ANSI_GREEN + "IC Number created successfully !\n" + ANSI_RESET);

        // Add customer to system
        adminOps.addCustomer(newCustomer);

        System.out.println(ANSI_GREEN + "\nCustomer added successfully! " + ANSI_RESET);
        new FinalChoice();
    }
}