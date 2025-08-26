package src.Contoller;

import java.util.ArrayList;

import src.Admin;
import src.AdminOperations;
import src.Customer;

public class Display {

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public Display() {
        System.out.println(ANSI_YELLOW + "====== View All Profiles ======" + ANSI_RESET);
        displayProfiles();
    }

    public void displayProfiles() {
        AdminOperations ao = new AdminOperations();
        ArrayList<Customer> customers = ao.viewAllProfiles();
        Customer cs = new Customer();

        if (customers.size() == 0) {
            System.out.println(ANSI_RED + "No customer profiles found!" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Possible reasons:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1. No customers have been added yet" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. The 'customers' directory doesn't exist or is empty" + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "Found " + customers.size() + " customer profile(s) : " + ANSI_RESET);
            System.out.println("-----------------------------------");

            for (int i = 0; i < customers.size(); i++) {
                System.out.println(ANSI_YELLOW + "Profile " + cs.getRole() + " # " + (i + 1) + ": " + ANSI_RESET);
                System.out.println(customers.get(i).toString());
                System.out.println("-----------------------------------");
            }
        }
        System.out.println(ANSI_YELLOW + "\nReturning to Admin menu..." + ANSI_RESET);
        new FinalChoice();
        new Admin();
    }
}
