package src;

/**
 * Interface defining common user interface functionality
 * for different user types in the vehicle rental system
 */
public interface UserInterface {
    
    /**
     * Display menu and handle user interactions
     */
    void displayMenu();
    
    /**
     * Show the menu options specific to this user type
     */
    void showMenuOptions();
    
    /**
     * Handle the user's menu selection
     * @param choice The menu option chosen by the user
     * @return boolean indicating whether to continue displaying the menu
     */
    boolean handleMenuChoice(int choice);
    
    /**
     * Utility method to get valid integer input from the user
     * @return The validated integer choice
     */
    int getValidChoice();
}