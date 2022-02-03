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
    private static ArrayList<Request> requests = new ArrayList<>();
    private static ArrayList<AnswerQuestion> answerQuestions = new ArrayList<>();

    public Banker() {
        id = total - 1;
        total--;
    }

    public Banker(int a) {
        id = total - 1;
        total--;
        this.getName().first_name = "xxx";
        this.getName().middle_name = "yyy";
        this.getName().last_name = "zzz";
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

    public Account withdraw(double amount, Account ac) {

        if (amount > ac.getAmount()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient Balance ");
            alert.showAndWait();
            return ac;
        }
        ac.setAmount(ac.getAmount() - amount);
        return ac;
    }

    public Account deposit(double amount, Account ac) {
        ac.setAmount(ac.getAmount() + amount);
        return ac;
    }

    public static void registerTransaction(Transaction t) {
        transactions.add(t);
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public static void discardRequest(Request request) {
        requests.remove(request);
    }

    public static void addRequests(Request request) {
        requests.add(request);
    }

    public Account searchList(long num, ArrayList<Account> accountList) {
        for (Account account : accountList) {
            if (num == account.getNumber()) {
                return account;
            }
        }
        return null;
    }

    public static void addQuestion(AnswerQuestion answerQuestion) {
        answerQuestions.add(answerQuestion);
    }

    public static ArrayList<AnswerQuestion> getAnswerQuestions() {
        return answerQuestions;
    }

    public void removeQuestion(AnswerQuestion answerQuestion) {
        answerQuestions.remove(answerQuestion);
    }
}
