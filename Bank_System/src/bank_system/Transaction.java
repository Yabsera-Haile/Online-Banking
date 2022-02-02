package bank_system;

public class Transaction {
    protected String type;
    protected DateList DoT;
    protected long sender,receiver;
    protected double amount_before, amount_after;
    protected double change= amount_after-amount_before;
    public void display()
    {
        System.out.println("display");
    }
}