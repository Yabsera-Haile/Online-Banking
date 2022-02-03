package bank_system;

public class Request {
    protected String type;
    protected double amount;
    protected Account requester=new Account();
    protected Banker Reveiwer=new Banker();
    protected DateList DoR=new DateList();
}
