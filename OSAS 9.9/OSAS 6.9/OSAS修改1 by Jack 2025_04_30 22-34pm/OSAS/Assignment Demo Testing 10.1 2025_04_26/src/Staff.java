package src;

public class Staff extends Person {
    private String staffID;
    private String position;
    private double salary;

    public Staff() {
        super();
    }

    public Staff(String firstName, String lastName, String licenseNumber, String phoneNumber, String email, String IC,
            String staffID, String position, double salary) {
        super(firstName, lastName, licenseNumber, phoneNumber, email, IC);
        this.staffID = staffID;
        this.position = position;
        this.salary = salary;
    }

    public Staff(String staffID, String position, double salary) {
        this.staffID = staffID;
        this.position = position;
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Staff ID      : " + getStaffID() + "\n" +
                "Position      : " + getPosition() + "\n" +
                "Salary        : RM" + String.format("%.2f", getSalary()) + "\n";
    }
}
