package bank_system;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import javax.swing.text.View;
import java.lang.*;

public class Account {
    private long number;
    private double amount;
    private String type;
    private static long total = 0;
    private Person owner = new Person();
    private boolean active;
    private ArrayList<Beneficairy> friends = new ArrayList<>();
    private String password = "1234";
    private ArrayList<String> notification = new ArrayList<>();
    private String fullname;

    public void setType(String type) {
        this.type = type;
    }

    public Account() {
        number = total + 1;
        total++;
        active = true;
    }

    public Account(int a) {
        number = total + 1;
        total++;
        active = true;
        this.owner.getName().first_name = "aaa";
        this.owner.getName().middle_name = "bbb";
        this.owner.getName().last_name = "ccc";
        this.fullname="aaa bbb ccc";
        this.amount = 2000;
    }

    public void setAccount(String fname, String mname, String lname, int d, int m, int y, String sex, String email,
            String tel, String type, double amount) {
        this.amount = amount;
        this.type = type;
        fullname = owner.setPerson(fname, mname, lname, d, m, y, sex, email, tel);
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean getActive() {
        return this.active;
    }

    public void delete() {
        total--;
    }

    public String getPassword() {
        return password;
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

    public long getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String send(long receiver, double amount, ArrayList<Account> accountList) {
        if (receiver == this.number) {
            return "Account Number not found";
        } else {
            if (amount > this.amount) {
                return "Your Account is Insufficient.";
            } else {
                int i = 0;
                for (Account temp : accountList) {
                    if (receiver == temp.number) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.setTitle("Empty Field");
                        alert.setHeaderText("confirmation");
                        alert.setContentText("Are you sure you want to transfer " + amount + "ETB to "
                                + temp.owner.getName().first_name + " "
                                + temp.owner.getName().middle_name + " " + temp.owner.getName().last_name);
                        Optional<ButtonType> option = alert.showAndWait();
                        if (option.get() == ButtonType.OK) {
                            this.amount -= amount;
                            temp.recieve(this.number, amount);
                            return "You have transfered " + amount + " ETB to Account Number " + temp.number
                                    + ". \nYour current balance is " + this.amount
                                    + "ETB.";
                        } else if (option.get() == ButtonType.CANCEL) {
                            return "Transfer Cancelled";
                        }

                    }
                }
                return "Account Number not found.";
            }
        }
    }

    public void recieve(long sender, double amount) {
        this.amount += amount;
        String re = "You have recieved " + amount + "ETB from " + sender + ". Your current balance is " + this.amount
                + ".";
        addNotification(re);
    }

    public String getType() {
        return type;
    }

    public AnswerQuestion askQuetion(String q) {
        AnswerQuestion a = new AnswerQuestion();
        a.setQuestion(q);
        a.setAccountno(this.number);
        return a;
    }

    public void addFriends(String name, long accountno, ArrayList<Account> accountList) {
        String i = null;
        if (accountno == this.number) {
            i = "You can't be your Own Benificiary.";
        }
        if (i == null)
            for (Beneficairy ben : friends) {
                if (accountno == ben.getAccountno()) {
                    i = "Beneficiary Already Exists.";
                }
            }
        if (i == null)
            for (Account temp : accountList) {
                if (accountno == temp.number) {
                    Beneficairy beneficairy = new Beneficairy(name, accountno);
                    friends.add(beneficairy);
                    i = "New Beneficairy added.";
                }
            }
        if (i == null) {
            i = "Account number not found.";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(i);
        alert.showAndWait();
    }

    public ArrayList<Beneficairy> returnFriends() {
        return friends;
    }

    public void deleteFriends(Beneficairy beneficairy) {
        friends.remove(beneficairy);
    }

    public ArrayList<String> getNotification() {
        return notification;
    }

    public void addNotification(String a) {
        notification.add(a);
    }

    public void seenNotification() {
        notification.clear();
    }

    public void recieveAnswer(AnswerQuestion a) {
        String b = "The Answer to you question to your question \"" + a.getQuestion() + "\" is: \"" + a.getAnswer()+"\"";
        this.addNotification(b);
    }

    public ArrayList<Beneficairy> getFriends() {
        return friends;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public void changeCondtion(boolean x) {
        if(x==true)
        this.active=false;
        if(x==false)
        this.active=true;    
    }
}
