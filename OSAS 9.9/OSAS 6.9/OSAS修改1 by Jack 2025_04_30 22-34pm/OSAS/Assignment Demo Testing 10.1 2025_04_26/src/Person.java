package src;

// Make Person abstract
public abstract class Person {
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phoneNumber;
    private String email;
    private String IC;

    public Person() {

    }

    public Person(String firstName, String lastName, String licenseNumber, String phoneNumber, String email,
            String IC) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        setIC(IC); // Use setter to enforce validation
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIC() {
        return IC;
    }

    public void setIC(String IC) {
        if (IC == null || !IC.matches("\\d{12}")) { // Validate 12 digits
            throw new IllegalArgumentException("IC must be 12 digits");
        }
        this.IC = IC;
    }

    public String toString() {
        return "First Name    : " + getFirstName() + "\n" +
                "Last Name     : " + getLastName() + "\n" +
                "LicenseNumber : " + getLicenseNumber() + "\n" +
                "Phone Number  : " + getPhoneNumber() + "\n" +
                "IC            : " + getIC() + "\n";
    }

    /**
     * Returns the role of the person in the system
     * 
     * @return role name
     */

    // Add abstract method
    public abstract String getRole();

}
