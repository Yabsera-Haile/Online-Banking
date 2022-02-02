package bank_system;

public class Asset {
    public Asset(String type, String location, double worth)
    {
        this.type=type;
        this.location=location;
        this.worth=worth;
    }
    private String type, location;
    private double worth;
    
}
