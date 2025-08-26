package src.Contoller;

import java.util.Scanner;
import src.Admin;
import src.Customer;
import src.CustomerOperations;
import src.Main;

public class CustomerRegistration {

    private Scanner scanner;
    private CustomerOperations customerOps;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public CustomerRegistration() {
        // Create a new Scanner object directly from System.in
        scanner = new Scanner(System.in);
        customerOps = new CustomerOperations();
        registerNewCustomer();
    }

    private void registerNewCustomer() {
        displayTitle();
        // Create a new Customer object
        Customer newCustomer = new Customer();

        // ------------- Get customer ID -------------
        String id;
        do {

            System.out.println("");
            System.out.print("\nPlease enter Customer ID (0 to go back to Menu, Q to quit): ");
            id = scanner.nextLine();

            if (id.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (id.equals("0")) {
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
                new WaitingTime();
                new Main();
                return;
            }

            if (id.isEmpty()) {
                System.out.println(ANSI_RED + " \nID cannot bee empty! Please try again ! " + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "\nFor Example : Daddy001, Delay No More6954 ... " + ANSI_RESET);
                System.out.println("");
                continue;
            }

            if (id.length() >= 20) {
                System.out.println(ANSI_RED + "ID cannot more than or equal 20 characters!" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please try again!" + ANSI_RESET);
                System.out.println("");
                continue;
            }

            if (customerOps.IDExists(id)) {
                System.out.println(ANSI_RED + "\n\t\t\tID already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\t\t   Please choose a different ID ! " + ANSI_RESET);
                id = "";
                System.out.println("");
            }
        } while (id.isEmpty());
        newCustomer.setID(id);

        // ------------- Get customer details -------------
        // Password no limit anything just do it what you want
        String password;
        do {
            System.out.println(
                    ANSI_YELLOW
                            + "\n*Take Note : Please do not set a password that is too complex, and remember it if necessary.\n"
                            + ANSI_RESET);
            System.out.print("Enter Password  (0 to go back to Menu, Q to quit): ");
            password = scanner.nextLine();

            if (password.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (password.equals("0")) {
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
                System.out.println("");
                new WaitingTime();
                new Main();
                return;
            }

            if (password.isEmpty()) {

                System.out.println(ANSI_RED + "Password cannot be empty ! " + ANSI_RESET);
                System.out.println("");
                continue;
            }
        } while (password.isEmpty());
        newCustomer.setPassword(password);
        System.out.println(ANSI_GREEN + "Password created successfully !\n" + ANSI_RESET);

        // ------------- First/Last Name: Ensure only alphabetic characters
        // -------------
        String firstName;
        do {
            System.out.print("Enter First Name (alphabetic characters only) (0 to go back to Menu, Q to quit): ");
            firstName = scanner.nextLine();

            if (firstName.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (firstName.equals("0")) {
                System.out.println(ANSI_YELLOW + "Returning to main menu..." + ANSI_RESET);
                System.out.println("");
                new WaitingTime();
                new Main();
                return;
            }

            if (firstName.isEmpty()) {
                System.out.println(ANSI_RED + "\nFirst Name cannot be empty !\n" + ANSI_RESET);
                System.out.println("");
                continue;
            }
            if (!firstName.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "\nFirst Name must contain only alphabetic characters !\n" + ANSI_RESET);
                firstName = "";
                System.out.println("");
            }

        } while (firstName.isEmpty());
        newCustomer.setFirstName(firstName);
        System.out.println(ANSI_GREEN + "First  Name created successfully !\n" + ANSI_RESET);

        // ------------- Get Last Name with validation -------------
        String lastName;
        do {
            System.out.print("\nEnter Last Name (alphabetic characters only) (0 to go back to Menu, Q to quit): ");
            lastName = scanner.nextLine();

            if (lastName.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (lastName.equals("0")) {
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
                System.out.println("");
                new WaitingTime();
                new Main();
                return;
            }

            if (lastName.isEmpty()) {
                System.out.println(ANSI_RED + "\nLast Name cannot be empty!" + ANSI_RESET);
                System.out.println("");
                continue;
            }
            if (!lastName.matches("[a-zA-Z ]+")) {
                System.out.println(ANSI_RED + "Last Name must contain only alphabetic characters!" + ANSI_RESET);
                lastName = "";
                System.out.println("");
            }
        } while (lastName.isEmpty());
        newCustomer.setLastName(lastName);
        System.out.println(ANSI_GREEN + "Last Name created successfully !\n" + ANSI_RESET);

        // ------------- Email: Must include '@' and 'mail.com' -------------
        String email;
        do {
            System.out.print("\nEnter Email (must include @ and mail.com) (0 to go back to Menu, Q to quit): ");
            email = scanner.nextLine();

            if (email.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (email.equals("0")) {
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
                new WaitingTime();
                new Main();
                return;
            }

            if (email.isEmpty()) {
                System.out.println(ANSI_RED + "Email cannot be empty!" + ANSI_RESET);
                continue;
            }
            if (!email.contains("@") || !email.contains("mail.com")) {
                System.out.println(ANSI_RED + "\nEmail must contain @ and mail.com!" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "\nFor example : Delaynomore@example.com" + ANSI_RESET);
                email = "";
            }
        } while (email.isEmpty());
        newCustomer.setEmail(email);
        System.out.println(ANSI_GREEN + "Email created successfully !\n" + ANSI_RESET);

        // ------------- Phone Number: Only numbers and cannot more than 12 digits
        // -------------
        String phoneNumber;
        do {
            System.out.print("Enter Phone Number (11 digits) (0 to go back to Menu, Q to quit): ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (phoneNumber.equals("0")) {
                System.out.println("");
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
                new WaitingTime();
                new Main();
                return;
            }

            if (phoneNumber.isEmpty()) {
                System.out.println("");
                System.out.println(ANSI_RED + "Phone Number cannot be empty!" + ANSI_RESET);
                continue;
            }
            if (!phoneNumber.matches("\\d+")) {
                System.out.println("");
                System.out.println(ANSI_RED + "Phone Number must contain only digits!" + ANSI_RESET);
                phoneNumber = "";
                continue;
            }
            if (phoneNumber.length() > 11 || phoneNumber.length() < 11) {
                System.out.println("");
                System.out.println(ANSI_RED + "Phone Number must be 11 digits!" + ANSI_RESET);
                phoneNumber = "";
            }
        } while (phoneNumber.isEmpty());
        newCustomer.setPhoneNumber(phoneNumber);
        System.out.println(ANSI_GREEN + "Phone Number created successfully !\n" + ANSI_RESET);

        String ic;
        do {
            System.out.print("Please enter Customer IC (0 to go back to Menu, Q to quit): ");
            ic = scanner.nextLine();

            if (ic.equalsIgnoreCase("Q")) {
                exitToMainMenu();
                return;
            }

            if (ic.equals("0")) {

                System.out.println("");
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
                new WaitingTime();
                new Main();
                return;
            }

            if (ic.isEmpty()) {
                System.out.println(ANSI_RED + "IC cannot bee empty! \n" + ANSI_RESET);
                System.out.println(ANSI_RED + "Please try again !" + ANSI_RESET);
                System.out.println("");
                continue;
            }
            if (!ic.matches("\\d+")) {

                System.out.println(ANSI_RED + "IC must contain only digits !\n" + ANSI_RESET);
                ic = "";
                System.out.println("");
                continue;
            }
            if (ic.length() > 12 || ic.length() < 12) {
                System.out.println(ANSI_RED + "\nIC must be exactly 12 digits!\n" + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "An example would be 850502-10-1234\n" + ANSI_RESET);
                ic = "";
                System.out.println("");
                continue;
            }
            if (customerOps.ICExists(ic)) { // Assuming you've added this method to CustomerOperations
                System.out.println(ANSI_RED + "\tIC already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\tPlease choose a different IC ! " + ANSI_RESET);
                ic = "";
                System.out.println("");
            }
        } while (ic.isEmpty());
        newCustomer.setIC(ic);
        System.out.println(ANSI_GREEN + "IC Number created successfully !\n" + ANSI_RESET);

        String licenseNumber;
        do {
            System.out.print(
                    "Enter your License Number (Example: ABC1234D) (0 to go back to Main Menu, Q to return to Admin Menu): ");
            licenseNumber = scanner.nextLine();

            // Check for Q exit option
            if (licenseNumber.equalsIgnoreCase("Q")) {
                System.out.println(
                        ANSI_YELLOW + "\nCancelling staff creation. Returning to admin menu...\n" + ANSI_RESET);
                new Admin();
                return;
            }

            if (licenseNumber.equals("0")) {
                System.out.println(ANSI_YELLOW + "\nReturning to main menu...\n" + ANSI_RESET);
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
            CustomerOperations customerOps = new CustomerOperations();

            if (customerOps.licenseNumberExists(licenseNumber)) {
                System.out.println(ANSI_RED + "\n\t\tLicense Number already exists ! " + ANSI_RESET);
                System.out.println(ANSI_RED + "\t\t Please enter a different License Number ! " + ANSI_RESET);
                licenseNumber = "";
            }
        } while (licenseNumber.isEmpty());
        newCustomer.setLicenseNumber(licenseNumber);
        System.out.println(ANSI_GREEN + "License Number is created successfully !\n" + ANSI_RESET);

        // Add customer to system
        customerOps.addCustomer(newCustomer);
        System.out.println("");
        System.out.println(ANSI_GREEN + "\n***** Customer added successfully ! *****" + ANSI_RESET);
        System.out.print("\nPress Enter to return to Main menu...");
        System.out.println("");
        scanner.nextLine();
        new WaitingTime();
        new Main();
    }

    public void displayTitle() {
        System.out
                .println(ANSI_YELLOW + "\n=================== Customer Registration ===================" + ANSI_RESET);
        System.out.println("");
        System.out.println(ANSI_YELLOW + "                     (Type \"Q\" to exit)" + ANSI_RESET);
        System.out.println("");
    }

    private void exitToMainMenu() {
        System.out.println(ANSI_YELLOW + "\nQuitting registration and returning to main menu..." + ANSI_RESET);
        System.out.println("");
        new WaitingTime();
        new Main();
    }
}
