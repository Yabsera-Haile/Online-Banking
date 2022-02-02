package bank_system;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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
        this.owner.getName().first_name = "a";
        this.owner.getName().middle_name = "b";
        this.owner.getName().last_name = "c";
        this.amount = 2000;
    }

    public void setAccount() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.print("Insert the type of Account: ");
        this.type = sc.nextLine();
        System.out.print("Insert the Starting Amount: ");
        this.setAmount(sc.nextDouble());
        sc.nextLine();
        System.out.println("Account has been created: Press Enter to return.");
        sc.nextLine();
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
                        /*
                         * System.out.println("Are you sure you want to transder " + amount + " to "
                         * + temp.owner.getName().first_name + " "
                         * + temp.owner.getName().middle_name + " " + temp.owner.getName().last_name
                         * + ".\n Enter Y to confirm or any other letter to deny.");
                         * char ch = sc.next().charAt(0);
                         * ch = Character.toLowerCase(ch);
                         * if (ch == 'y') {
                         * this.amount -= amount;
                         * temp.recieve(this.number, amount);
                         * System.out.print("\033[H\033[2J");
                         * System.out.println("You have transfered " + amount +
                         * " ETB to Account Number " + temp.number
                         * + ". \nYour current balance is " + this.amount
                         * + ".\n Press Enter to return to previous page.");
                         * i++;
                         * sc.nextLine();
                         * } else
                         * i++;
                         * }
                         * }
                         */

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

    public AnswerQuestion askQuetion() {
        Scanner sc = new Scanner(System.in);
        AnswerQuestion a = new AnswerQuestion();
        System.out.print("\033[H\033[2J");
        System.out.print("Enter Your Question: ");
        a.question = sc.nextLine();
        a.accountno = this.number;
        System.out.println("Your question has been recieved, It will answered shortly.\nPlease press Enter to Return.");
        sc.nextLine();
        return a;
    }

    public int addFriends(String name, long accountno, ArrayList<Account> accountList) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        if (accountno == this.number) {
            System.out.println("You can't be your Own Benificiary.\n Please Press Enter to Return.");
            sc.nextLine();
            return 0;
        }
        for (Beneficairy ben : friends) {
            if (accountno == ben.getAccountno()) {
                System.out.println("Beneficiary Already Exists.\nPress Enter to Return.");
                sc.nextLine();
                return 0;
            }
        }
        for (Account temp : accountList) {
            if (accountno == temp.number) {
                Beneficairy beneficairy = new Beneficairy(name, accountno);
                friends.add(beneficairy);
                System.out.println("New Beneficairy added.\nPress Enter to Return.");
                sc.nextLine();
                i++;
                return 0;
            }
        }
        if (i == 0) {
            System.out.println("Account number not found.\n Please Press Enter to Return.");
            sc.nextLine();
        }
        return 0;
    }

    public void viewFriends(int a) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        System.out.println("N)  Name\tAccount Number");
        System.out.println("====================================================");
        for (Beneficairy temp : friends) {
            i++;
            System.out.println(i + ") " + temp.getName() + "  " + temp.getAccountno());
        }
        if (i == 0)
            System.out.println("No Regisered Beneficiaries.");
        if (a == 0) {
            System.out.println("Press Enter to return.");
            sc.nextLine();
        }
    }

    public void deleteFriends() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the account number of the beneficiary you wish to delete: ");
        long check = sc.nextLong();
        int i = 0, j = 0;
        Beneficairy t;
        for (Beneficairy temp : friends) {
            if (temp.getAccountno() == check) {
                j++;
                break;
            } else {
                i++;
            }
        }
        if (j == 0) {
            System.out.println("Beneficiary hasn't been found.\nPress Enter to return.");
            sc.nextLine();
        } else {
            friends.remove(i);
            System.out.println("Beneficiary has been deleted.\nPress Enter to return.");
            sc.nextLine();
        }
    }

    public Request loanRequest() {
        System.out.print("\033[H\033[2J");
        Scanner sc = new Scanner(System.in);
        Request request = new Request();
        System.out.print("Enter the Amount you wish to request: ");
        request.amount = sc.nextDouble();
        request.requester = this;
        System.out.println("\tAsset Detail");
        System.out.println("\t============");
        System.out.print("Enter the Type of Asset you wish to put as laiability(Eg. Building,car...): ");
        String type = sc.nextLine();
        System.out.print("Enter the Location of the Asset if Static and enter movable if dynamic: ");
        String location = sc.nextLine();
        System.out.print("Enter an official estimated worth of the asset: ");
        double amount = sc.nextDouble();
        Asset asset = new Asset(type, location, amount);
        request.r_asset = asset;
        System.out.println("Your request has been sent, a reply will be sent shortly.\n Press Enter to return.");
        sc.nextLine();
        return request;
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
        String b = "The Answer to you question to your question " + a.question + "is: \n" + a.answer;
        this.addNotification(b);
    }

    public ArrayList<Beneficairy> getFriends() {
        return friends;
    }

}
