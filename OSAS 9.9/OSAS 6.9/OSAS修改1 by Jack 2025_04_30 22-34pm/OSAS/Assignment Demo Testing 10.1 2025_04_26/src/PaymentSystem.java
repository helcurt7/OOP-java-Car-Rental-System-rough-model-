package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PaymentSystem {

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public PaymentSystem() {
        // Empty constructor - no need to call PaymentRental with empty values
    }

    public boolean PaymentRental(String productName, double totalAmount) {
        Scanner scanner = new Scanner(System.in);
        boolean scannerCreatedLocally = true; // scanner is created locally

        try {
            // Display payment options
            System.out.println(YELLOW + "\n╔════════════════════════════╗" + RESET);
            System.out.println(YELLOW + "║      PAYMENT METHODS       ║" + RESET);
            System.out.println(YELLOW + "║ (Type \"" + RED + "Q" + YELLOW + "\" to exit)         ║" + RESET);
            System.out.println(YELLOW + "╠════════════════════════════╣" + RESET);
            System.out.println(BLUE + "║ 1. Credit/Debit Card       ║" + RESET);
            System.out.println(BLUE + "║ 2. TNG eWallet             ║" + RESET);
            System.out.println(BLUE + "║ 3. Cash                    ║" + RESET);
            System.out.println(YELLOW + "╚════════════════════════════╝" + RESET);

            int paymentChoice = 0;
            boolean validChoice = false;

            // Validate payment choice input (must be numeric and between 1-3)
            while (!validChoice) {
                System.out.print(CYAN + "Enter your choice (1-3 or " + RED + "Q" + CYAN + " to exit): " + RESET);
                String input = scanner.nextLine();

                // Check for exit command
                if (input.equalsIgnoreCase("Q")) {
                    System.out.println(RED + "Payment cancelled. Returning to previous menu..." + RESET);
                    return false;
                }

                try {
                    paymentChoice = Integer.parseInt(input);
                    if (paymentChoice >= 1 && paymentChoice <= 3) {
                        validChoice = true;
                    } else {
                        System.out.println(RED + "Invalid choice. Please enter a number between 1 and 3." + RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Invalid input. Please enter a numeric value or 'Q' to exit." + RESET);
                }
            }

            String paymentMethod;
            String paymentDetails = "";

            switch (paymentChoice) {
                case 1:
                    paymentMethod = "Credit/Debit Card";
                    // Handle credit/debit card input with error handling
                    String cardNumber;
                    boolean validCardNumber = false;

                    do {
                        System.out.print(CYAN + "Enter card number (XXXX-XXXX-XXXX-XXXX) or " + RED + "Q" + CYAN
                                + " to exit: " + RESET);
                        cardNumber = scanner.nextLine();

                        // Check for exit command
                        if (cardNumber.equalsIgnoreCase("Q")) {
                            System.out.println(RED + "Payment cancelled. Returning to previous menu..." + RESET);
                            return false;
                        }

                        if (!isValidCardNumber(cardNumber)) {
                            System.out.println(
                                    RED + "Invalid card number format. Use format XXXX-XXXX-XXXX-XXXX with digits only."
                                            + RESET);
                        } else {
                            validCardNumber = true;
                        }
                    } while (!validCardNumber);

                    String expDate;
                    boolean validExpDate = false;

                    do {
                        System.out.print(
                                CYAN + "Enter expiration date (MM/YY) or " + RED + "Q" + CYAN + " to exit: " + RESET);
                        expDate = scanner.nextLine();

                        // Check for exit command
                        if (expDate.equalsIgnoreCase("Q")) {
                            System.out.println(RED + "Payment cancelled. Returning to previous menu..." + RESET);
                            return false;
                        }

                        if (!isValidExpiryDate(expDate)) {
                            System.out.println(
                                    RED + "Invalid expiration date format. Use MM/YY format with digits only." + RESET);
                        } else {
                            validExpDate = true;
                        }
                    } while (!validExpDate);

                    String cvv;
                    boolean validCVV = false;

                    do {
                        System.out.print(CYAN + "Enter CVV or " + RED + "Q" + CYAN + " to exit: " + RESET);
                        cvv = scanner.nextLine();

                        // Check for exit command
                        if (cvv.equalsIgnoreCase("Q")) {
                            System.out.println(RED + "Payment cancelled. Returning to previous menu..." + RESET);
                            return false;
                        }

                        if (!isValidCVV(cvv)) {
                            System.out.println(RED + "Invalid CVV. Must be 3 digits only." + RESET);
                        } else {
                            validCVV = true;
                        }
                    } while (!validCVV);

                    paymentDetails = "Card: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4)
                            + " | Exp: " + expDate;
                    break;
                case 2:
                    paymentMethod = "TNG eWallet";
                    // Handle TNG phone number input with error handling
                    String phoneNumber;
                    boolean validPhoneNumber = false;

                    do {
                        System.out.print(CYAN + "Enter TNG phone number or " + RED + "Q" + CYAN + " to exit: " + RESET);
                        phoneNumber = scanner.nextLine();

                        // Check for exit command
                        if (phoneNumber.equalsIgnoreCase("Q")) {
                            System.out.println(RED + "Payment cancelled. Returning to previous menu..." + RESET);
                            return false;
                        }

                        if (!isValidPhoneNumber(phoneNumber)) {
                            System.out.println(RED + "Invalid phone number format. Must be 10 digits only." + RESET);
                        } else {
                            validPhoneNumber = true;
                        }
                    } while (!validPhoneNumber);

                    paymentDetails = "Phone: " + phoneNumber;
                    break;
                case 3:
                    paymentMethod = "Cash";
                    double amountTendered = 0;
                    boolean validCashInput = false;
                    do {
                        System.out.print(CYAN + "Enter amount tendered (must be at least RM "
                                + String.format("%.2f", totalAmount) + ") or " + RED + "Q" + CYAN + " to exit: RM "
                                + RESET);

                        String cashInput = scanner.nextLine();

                        // Check for exit command
                        if (cashInput.equalsIgnoreCase("Q")) {
                            System.out.println(RED + "Payment cancelled. Returning to previous menu..." + RESET);
                            return false;
                        }

                        try {
                            amountTendered = Double.parseDouble(cashInput);
                            if (amountTendered < totalAmount) {
                                System.out.println(
                                        RED + "Insufficient amount. Please enter an amount equal to or greater than RM "
                                                + String.format("%.2f", totalAmount) + RESET);
                            } else {
                                validCashInput = true; // Valid number and enough money
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    RED + "Invalid input. Please enter a numeric value only or 'Q' to exit." + RESET);
                        }
                    } while (!validCashInput);

                    double change = amountTendered - totalAmount;
                    paymentDetails = "Tendered: RM " + String.format("%.2f", amountTendered)
                            + " | Change: RM " + String.format("%.2f", change);
                    break;

                default:
                    System.out.println(RED + "Invalid payment method selected. Defaulting to Cash." + RESET);
                    paymentMethod = "Cash";
            }

            // Get current date and time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            // Print fancy receipt
            printReceipt(productName, totalAmount, paymentMethod, paymentDetails, formattedDateTime);

            // Ask if the user wants to continue or return to main menu
            System.out.println(YELLOW + "\nOptions:" + RESET);
            System.out.println(BLUE + "1. Return to Main Menu" + RESET);
            System.out.println(RED + "Q. Quit" + RESET);
            System.out.print(CYAN + "Enter your choice (1 or Q): " + RESET);

            String finalChoice = scanner.nextLine();
            if (finalChoice.equalsIgnoreCase("Q")) {
                System.out.println(YELLOW + "Thank you for your payment. Exiting..." + RESET);
                System.exit(0);
                return true;
            } else {
                System.out.println(GREEN + "Returning to main menu..." + RESET);
                return true;
            }

        } catch (Exception e) {
            System.out.println(RED + "An error occurred during payment processing: " + e.getMessage() + RESET);
            e.printStackTrace();
            return false;
        } finally {
            if (scannerCreatedLocally) {
                scanner.close(); // Only close if we created it
            }
        }
    }

    // Card number validation - must be in format XXXX-XXXX-XXXX-XXXX with digits
    // only
    private boolean isValidCardNumber(String cardNumber) {
        // Checks if the card number follows the pattern and contains only digits and
        // hyphens
        if (!cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
            return false;
        }

        // Additional check to ensure no alphabetic characters
        for (char c : cardNumber.toCharArray()) {
            if (Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    // Expiration date validation (MM/YY format) - digits only
    private boolean isValidExpiryDate(String expDate) {
        // Basic format check
        if (!expDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            return false;
        }

        // Ensure no alphabetic characters
        for (char c : expDate.toCharArray()) {
            if (Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    // CVV validation (3 digits) - no alphabets allowed
    private boolean isValidCVV(String cvv) {
        // Must be exactly 3 digits
        if (!cvv.matches("\\d{3}")) {
            return false;
        }

        // Additional check for alphabetic characters
        for (char c : cvv.toCharArray()) {
            if (Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    // Phone number validation - digits only
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Must be 10 digits
        if (!phoneNumber.matches("\\d{10}")) {
            return false;
        }

        // Additional check for alphabetic characters
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    private void printReceipt(String productName, double totalAmount, String paymentMethod, String paymentDetails,
            String formattedDateTime) {

        System.out.println();
        System.out.println(YELLOW + "   _________________________________________________________________" + RESET);
        System.out.println(YELLOW + "  /                                                               \\" + RESET);
        System.out.println(YELLOW + " |                  " + CYAN + "CAR RENTAL INVOICE " + YELLOW
                + "                             |" + RESET);
        System.out.println(YELLOW + " |   _______                                                        |" + RESET);
        System.out.println(YELLOW + " |  //  ||\\ \\          " + GREEN + "~ Speedy Car Rentals ~ " + YELLOW
                + "                     |" + RESET);
        System.out.println(YELLOW + " | _____||_\\ \\___                                                   |" + RESET);
        System.out.println(YELLOW + " | )  _          _    \\                                             |" + RESET);
        System.out.println(YELLOW + " | |_/ \\________/ \\___|                                             |" + RESET);
        System.out.println(YELLOW + " |   \\_/        \\_/                                                 |" + RESET);
        System.out.println(YELLOW + " |                                                                  |" + RESET);
        System.out.println(YELLOW + " |  " + BLUE + "Date/Time: " + RESET + padRight(formattedDateTime, 50) + YELLOW
                + "   |" + RESET);
        System.out.println(
                YELLOW + " |  " + BLUE + "Vehicle:   " + RESET + padRight(productName, 50) + YELLOW + "   |" + RESET);
        System.out.println(YELLOW + " |------------------------------------------------------------------|" + RESET);
        System.out.println(
                YELLOW + " |  " + CYAN + "PAYMENT SUMMARY                                                 |" + RESET);
        System.out.println(YELLOW + " |  " + GREEN + "Subtotal:        " + RESET
                + padRight("RM " + String.format("%.2f", totalAmount * 0.94), 45) + YELLOW + "  |" + RESET);
        System.out.println(YELLOW + " |  " + GREEN + "SST (6%):        " + RESET
                + padRight("RM " + String.format("%.2f", totalAmount * 0.06), 45) + YELLOW + "  |" + RESET);
        System.out.println(YELLOW + " |  " + GREEN + "Total Amount:    " + RESET
                + padRight("RM " + String.format("%.2f", totalAmount), 45)
                + YELLOW + "  |" + RESET);
        System.out.println(YELLOW + " |                                                                  |" + RESET);
        System.out.println(YELLOW + " |  " + BLUE + "Payment Method:  " + RESET + padRight(paymentMethod, 45) + YELLOW
                + "  |" + RESET);
        System.out.println(YELLOW + " |  " + BLUE + "Payment Details: " + RESET + padRight(paymentDetails, 45) + YELLOW
                + "  |" + RESET);
        System.out.println(YELLOW + " |------------------------------------------------------------------|" + RESET);
        System.out.println(
                YELLOW + " |  " + RED + "IMPORTANT NOTES:                                                 |" + RESET);
        System.out.println(YELLOW + " |  • Please return vehicle with same fuel level                    |" + RESET);
        System.out.println(YELLOW + " |  • Late returns subject to additional charges                    |" + RESET);
        System.out.println(YELLOW + " |  • Contact: 03-1234 5678 | info@speedyrentals.com                |" + RESET);
        System.out.println(YELLOW + " |                                                                  |" + RESET);
        System.out.println(
                YELLOW + " |  " + PURPLE + "Thank you for your business!                                    |" + RESET);
        System.out.println(YELLOW + " \\__________________________________________________________________/" + RESET);

    }

    private String padRight(String s, int length) {
        if (s == null)
            s = "";
        return String.format("%-" + length + "s", s);
    }
}
