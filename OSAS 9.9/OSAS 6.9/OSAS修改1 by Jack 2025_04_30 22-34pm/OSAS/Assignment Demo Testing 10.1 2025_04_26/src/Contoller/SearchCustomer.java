package src.Contoller;

import java.util.ArrayList;
import java.util.Scanner;
import src.Admin;
import src.AdminOperations;
import src.Customer;

public class SearchCustomer {

    private Scanner scanner;
    private AdminOperations ao;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public SearchCustomer() {
        scanner = new Scanner(System.in);
        ao = new AdminOperations();
        displaySearchMenu();
    }

    private void displaySearchMenu() {
        System.out.println(ANSI_YELLOW + "\n======= Search Customer =======" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. Search by ID " + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2. Back to Admin Menu" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + "\nEnter your choice: " + ANSI_RESET);

        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
            displaySearchMenu();
            return;
        }

        switch (choice) {
            case 1:
                searchCustomerById();
                break;
            case 2:
                goBack();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                displaySearchMenu();
        }
    }

    public void searchCustomerById() {
        System.out.print(ANSI_YELLOW + "Enter customer ID to search: " + ANSI_RESET);
        String id = scanner.nextLine();

        ArrayList<Customer> arr = ao.searchByCustomerID(id);
        displayResults(arr);
    }

    // public void searchCustomerByIC() {
    // System.out.print("Enter customer IC to search: ");
    // String ic = scanner.nextLine();

    // ArrayList<Customer> arr = ao.searchByCustomerID(ic);
    // displayResults(arr);
    // }

    private void displayResults(ArrayList<Customer> arr) {
        if (arr.size() == 0) {
            System.out.println(ANSI_RED + "No matching records found !" + ANSI_RESET);
        } else {
            System.out.println(ANSI_YELLOW + "\n----- Search Results -----" + ANSI_RESET);
            for (int i = 0; i < arr.size(); i++) {
                String details = arr.get(i).toString();
                System.out.println(ANSI_GREEN + details + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "--------------------------" + ANSI_RESET);
            }
        }
        System.out.print(ANSI_YELLOW + "Press Enter to return to search menu..." + ANSI_RESET);
        scanner.nextLine();
        displaySearchMenu();
    }

    private void goBack() {
        System.out.println(ANSI_YELLOW + "Returning to Admin menu..." + ANSI_RESET);
        new FinalChoice();
        new Admin();
    }
}
