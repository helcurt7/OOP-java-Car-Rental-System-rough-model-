package src;

public class Customer extends Person{
    private String ID;
    private String password;
    
    public Customer(){
        super();
    }

    public Customer(String firstName, String lastName, String licenseNumber, String phoneNumber, String email,String IC,String ID,  String password) {
        super(firstName, lastName, licenseNumber, phoneNumber, email,IC);
        this.ID = ID;
        this.password = password;
    }

    public Customer(String iD, String password) {
        this.ID = iD;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        this.ID = iD;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString()
    {
        return
                super.toString() +
                "Customer ID  : " + getID() + "\n" +
                "Password     : " + getPassword() + "\n";
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}
