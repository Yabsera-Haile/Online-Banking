package bank_system;

public class Request {
    private String type;
    private double amount;
    private long requester;
    private Banker Reveiwer=new Banker();
    private DateList DoR=new DateList();
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDoR(DateList doR) {
        DoR = doR;
    }
    public void setRequester(long requester) {
        this.requester = requester;
    }
    public void setReveiwer(Banker reveiwer) {
        Reveiwer = reveiwer;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public DateList getDoR() {
        return DoR;
    }
    public long getRequester() {
        return requester;
    }
    public Banker getReveiwer() {
        return Reveiwer;
    }
    public String getType() {
        return type;
    }
    
}
