package bank_system;

public class Beneficairy {
    private String name;
    private long accountno;
    public Beneficairy(String name, long accountno)
    {
        this.accountno=accountno;
        this.name=name;
    }
    public long getAccountno() {
        return accountno;
    }
    public String getName() {
        return name;
    }
}
