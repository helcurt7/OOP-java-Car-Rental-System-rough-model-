package src;

public class Vehicle {
    private int carID;
    private String brand;
    private String model;
    private int year;
    private double rentalRate;
    private static int nextCarID = 1000;
    private String status; // Added status field

    // For creating brand new vehicles (when carID is 0)
    public Vehicle(int carID, String brand, String model, int year, double rentalRate) {
        if (carID == 0) {
            this.carID = nextCarID++;
        } else {
            this.carID = carID; // Use the exact ID provided
        }
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalRate = rentalRate;
        this.status = "Available"; // Default status
    }

    // Constructor with status
    public Vehicle(int carID, String brand, String model, int year, double rentalRate, String status) {
        this(carID, brand, model, year, rentalRate);
        this.status = status;
    }

    // Default constructor
    public Vehicle() {
        this.carID = nextCarID++;
        this.brand = "";
        this.model = "";
        this.year = 0;
        this.rentalRate = 0.0;
        this.status = "Available";
    }

    // Rest of the class remains the same
    public void displayInfo() {
        System.out.println("Car ID: " + carID);
        System.out.println("Brand:" + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Rental Rate: $" + rentalRate + " per day");
        System.out.println("Status: " + status);
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    // Add to Vehicle class
    public static void setNextCarID(int id) {
        nextCarID = id;
    }

    // Add status getter and setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car ID: " + carID +
                " | Brand: " + brand +
                " | Model: " + model +
                " | Year: " + year +
                " | Rate: $" + rentalRate + "/day" +
                " | Status: " + status;
    }
}