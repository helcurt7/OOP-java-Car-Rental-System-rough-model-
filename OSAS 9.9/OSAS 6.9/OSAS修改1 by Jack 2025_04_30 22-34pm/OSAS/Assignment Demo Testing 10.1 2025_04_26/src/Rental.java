package src;

public class Rental {
    private String rentalID;
    private String customerID;
    private int carID;
    private String rentalDate;
    private int days;
    private boolean returned;
    private String returnDate;
    
    public Rental(String rentalID, String customerID, int carID, String rentalDate, int days, boolean returned, String returnDate) {
        this.rentalID = rentalID;
        this.customerID = customerID;
        this.carID = carID;
        this.rentalDate = rentalDate;
        this.days = days;
        this.returned = returned;
        this.returnDate = returnDate;
    }
    
    public String getRentalID() {
        return rentalID;
    }
    
    public String getCustomerID() {
        return customerID;
    }
    
    public int getCarID() {
        return carID;
    }
    
    public String getRentalDate() {
        return rentalDate;
    }
    
    public int getDays() {
        return days;
    }
    
    public boolean isReturned() {
        return returned;
    }
    
    public String getReturnDate() {
        return returnDate;
    }
    
    public double calculateTotalCost(double rentalRate) {
        return rentalRate * days;
    }
    
    @Override
    public String toString() {
        return "Rental ID: " + rentalID + 
               "\nCustomer ID: " + customerID + 
               "\nCar ID: " + carID + 
               "\nRental Date: " + rentalDate + 
               "\nDays: " + days + 
               "\nReturned: " + returned + 
               (returned ? "\nReturn Date: " + returnDate : "") +
               "\n";
    }
}