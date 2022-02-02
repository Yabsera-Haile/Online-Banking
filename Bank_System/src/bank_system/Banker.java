package bank_system;

import java.util.Scanner;
import java.util.ArrayList;

public class Banker extends Person {
    private long id;
    private static long total = -1;
    private String password = "1234";
    protected static ArrayList<Transaction> transactions = new ArrayList<>();

    public void setBanker() {
        id = total - 1;
        total--;
    }

    public int changePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.print("Enter Old Password: ");
        String old_password = sc.nextLine();
        if (old_password.equals(password)) {
            System.out.print("Enter New Password: ");
            String new_password = sc.nextLine();
            System.out.print("Confirm New Password: ");
            String confirm_password = sc.nextLine();
            if (new_password.equals(confirm_password)) {
                password = new_password;
                System.out.println("Password has been Changed, Press Enter Return ");
                sc.nextLine();
            } else {
                System.out
                        .print("Password Don't Match Enter R to Try Again and Any Other Key to Return to Home Page: ");
                char a = sc.next().charAt(0);
                a = Character.toLowerCase(a);
                if (a == 'r') {
                    changePassword();
                } else {
                    return 0;
                }
            }
        } else {
            System.out.print("Wrong Password Enter R to Try Again and Any Other Key to Return to Home Page: ");
            char a = sc.next().charAt(0);
            a = Character.toLowerCase(a);
            if (a == 'r') {
                changePassword();
            } else {
                return 0;
            }
        }
        return 1;
    }

    public Account createAnAccount(ArrayList<Account> accountList) {
        System.out.print("\033[H\033[2J");
        Account newacc = new Account();
        for (Account account : accountList) {
            if (account.getActive() == false) 
            {
                newacc.setNumber(account.getNumber());
                account=newacc;
                account.delete();
                break;
            }
        }
        return newacc;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void debit(double amount, Account newacc) {

        newacc.setAmount(newacc.getAmount() - amount);
        System.out.println("Transaction succesful, press Enter to continue. ");
        sc.nextLine();
    }

    public void deposit(double amount, Account newacc) {
        newacc.setAmount(newacc.getAmount() + amount);
        System.out.println("Transaction succesful, press Enter to continue. ");
        sc.nextLine();
    }

    public static void registerTransaction(Transaction t) {
        transactions.add(t);
    }

    public void searchTransaction1()
    {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter the Account Number of the Customer involved: ");
        long a=sc.nextLong();
        int i=0;
        for(Transaction t: transactions)
        {
            if(t.receiver==a || t.sender==a)
            {
                t.display();
                i++;
            }
        }
        if(i==0)
        {
            System.out.println("Not Found.");
        }
    }
    
    public void searchTransaction2() {
        System.out.print("\033[H\033[2J");
        DateList d=new DateList();
        int i = 0;
        for (Transaction t : transactions) {
            if (t.DoT == d)
             {
                t.display();
                i++;
            }
        }
        if (i == 0) {
            System.out.println("Not Found.");
        }
    }
    public void printTransaction()
    {
        for(Transaction t:transactions)
        {
            t.display();
        }
    }
}
