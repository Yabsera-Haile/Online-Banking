package bank_system;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Banker extends Person {
    private long id;
    private static long total = -1;
    private String password = "1234";
    protected static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<Request> requests=new ArrayList<>();
    public Banker() {
        id = total - 1;
        total--;
    }

    public Banker(int a) {
        id = total - 1;
        total--;
        this.getName().first_name = "aaa";
        this.getName().middle_name = "bbb";
        this.getName().last_name = "ccc";
    }

    public String changePassword(String op, String np, String cp) {
        if (op.equals(password)) {
            if (np.equals(cp)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Empty Field");
                alert.setHeaderText("confirmation");
                alert.setContentText("Are you sure you want to change the Password");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    password = np;
                    return "Password has been Changed";
                } else if (option.get() == ButtonType.CANCEL) {
                    return "Transfer Cancelled";
                }

            } else {
                return "Confirm Password Don't Match ";
            }
        } else {
            return "Wrong Password Entered ";
        }
        return " ";
    }

    public Account createAnAccount(ArrayList<Account> accountList) {
        System.out.print("\033[H\033[2J");
        Account newacc = new Account();
        for (Account account : accountList) {
            if (account.getActive() == false) {
                newacc.setNumber(account.getNumber());
                account = newacc;
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

    public void searchTransaction1() {
        System.out.print("\033[H\033[2J");
        System.out.print("Enter the Account Number of the Customer involved: ");
        long a = sc.nextLong();
        int i = 0;
        for (Transaction t : transactions) {
            if (t.receiver == a || t.sender == a) {
                t.display();
                i++;
            }
        }
        if (i == 0) {
            System.out.println("Not Found.");
        }
    }

    public void searchTransaction2() {
        System.out.print("\033[H\033[2J");
        DateList d = new DateList();
        int i = 0;
        for (Transaction t : transactions) {
            if (t.DoT == d) {
                t.display();
                i++;
            }
        }
        if (i == 0) {
            System.out.println("Not Found.");
        }
    }
    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public static ArrayList<Request> getRequests() {
        return requests;
    }
    public void printTransaction() {
        for (Transaction t : transactions) {
            t.display();
        }
    }
}
