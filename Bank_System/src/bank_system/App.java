package bank_system;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    private static ArrayList<Account> accountlist = new ArrayList<>();
    private static ArrayList<Banker> bankerlist = new ArrayList<>();
    private static String admipass = "Password";
    private static Account account;
    private static Banker banker;
    private static char usertype = 'd';
    MyException exception = new MyException();

    public char login(String pass, long num) {
        Scanner input = new Scanner(System.in);
        pass = input.nextLine();
        if (num == -1) {
            if (pass.equals(admipass)) {
                return 'a';
            }
        }
        for (Banker b : bankerlist) {
            if (num == b.getId()) {
                if (pass.equals(b.getPassword())) {
                    banker = b;
                    return 'b';
                }
            }
        }
        for (Account a : accountlist) {
            if (num == a.getNumber()) {
                if (pass.equals(a.getPassword())) {
                    account = a;
                    return 'c';
                }
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Incorrect Username or Password!");
        return 'd';
    }

    public void ustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.println("====================================================");
        System.out.println("\t\tWelcome!");
        System.out.println("====================================================");
        System.out.println("\t\tInformation");
        System.out.println("====================================================");
        System.out.println("Name: " + account.getOwner().getName().first_name + " "
                + account.getOwner().getName().middle_name + " " + account.getOwner().getName().last_name);
        System.out.println("Account Number: " + account.getNumber());
        System.out.println("Current Amount: " + account.getAmount());
        System.out.println("====================================================");
        System.out.println("\t\tOperations");
        System.out.println("====================================================");
        System.out.println("A) Transfer");
        System.out.println("B) Request for Loan");
        System.out.println("C) Ask Question");
        System.out.println("D) Manage Beneficiaries");
        System.out.println("E) Change Password");
        System.out.println("N) Notification(" + account.getNotification().size() + ") ");
        System.out.println("L) Log Out");
        System.out.println("====================================================");
        System.out.print("Choice: ");
        char ch = sc.next().charAt(0);
        ch = Character.toLowerCase(ch);
        switch (ch) {
            case 'a':
                System.out.print("\033[H\033[2J");
                System.out.println("====================================================");
                System.out.println("\t\tOperations");
                System.out.println("====================================================");
                System.out.println("A) Enter the Account");
                System.out.println("B) Choose from Beneficiaries");
                System.out.println("====================================================");
                System.out.print("Choice: ");
                char ch1 = sc.next().charAt(0);
                ch1 = Character.toLowerCase(ch1);
                Transaction t = new Transaction();
                t.type = "Transfer";
                t.DoT.getCurrDate();
                t.amount_before = account.getAmount();
                t.sender = account.getNumber();
                switch (ch1) {
                    case 'a':
                        System.out.print("\033[H\033[2J");
                        System.out.print("Enter the account number of the customer you wish to transfer: ");
                        long receiver = sc.nextLong();
                        t.receiver = receiver;
                        System.out.print("Enter the amount you wish to transfer: ");
                        double amount = sc.nextDouble();
                        account.send(receiver, amount, accountlist);
                        t.amount_after = account.getAmount();
                        Banker.registerTransaction(t);
                        customer();
                        break;
                    case 'b':
                        System.out.print("\033[H\033[2J");
                        System.out.println("Beneficiary List");
                        System.out.println("====================================================");
                        account.viewFriends(1);
                        System.out.println("====================================================");
                        System.out.print("Choose the number of the Beneficiary: ");
                        int a = sc.nextInt() - 1;
                        System.out.print("Enter the amount you wish to transfer: ");
                        double amoun = sc.nextDouble();
                        t.receiver = account.getFriends().get(a).getAccountno();
                        account.send(account.getFriends().get(a).getAccountno(), amoun, accountlist);
                        t.amount_after = account.getAmount();
                        Banker.registerTransaction(t);
                        customer();
                        break;
                }
                break;
            case 'b':
                Request request = new Request();
                request = account.loanRequest();
                break;
            case 'c':
                account.askQuetion();
                customer();
                break;
            case 'd':
                System.out.print("\033[H\033[2J");
                System.out.println("====================================================");
                System.out.println("A) Add Beneficiary");
                System.out.println("B) View Beneficiaries");
                System.out.println("C) Delete Beneficiary");
                System.out.println("====================================================");
                System.out.print("Choice: ");
                char ch2 = sc.next().charAt(0);
                ch2 = Character.toLowerCase(ch2);
                switch (ch2) {
                    case 'a':
                        System.out.print("\033[H\033[2J");
                        System.out.print("Enter name of the Beneficiary: ");
                        sc.nextLine();
                        String n = sc.nextLine();
                        System.out.print("Enter account number of the Beneficiary: ");
                        long a = sc.nextLong();
                        account.addFriends(n, a, accountlist);
                        customer();
                        break;
                    case 'b':
                        System.out.print("\033[H\033[2J");
                        account.viewFriends(0);
                        customer();
                        break;
                    case 'c':
                        System.out.print("\033[H\033[2J");
                        account.deleteFriends();
                        customer();
                        break;
                }
                break;
            case 'n':
                System.out.print("\033[H\033[2J");
                int i = 0;
                for (String nt : account.getNotification()) {
                    System.out.println(i + ") " + nt);
                }
                System.out.println("Press Enter to Return.");
                sc.nextLine();
                customer();
                break;
            case 'l':
                logout();
                break;
        }
    }

    public void admin() {
        System.out.print("\033[H\033[2J");
        System.out.println("Welcome Admin!");
    }

    public void employee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.println("====================================================");
        System.out.println("\t\tWelcome!");
        System.out.println("====================================================");
        System.out.println("\t\tInformation");
        System.out.println("====================================================");
        System.out.println("Name: " + banker.getName().first_name + " "
                + banker.getName().middle_name + " " + banker.getName().last_name);
        System.out.println("Employee Id: " + banker.getId());
        System.out.println("====================================================");
        System.out.println("\t\tOperations");
        System.out.println("====================================================");
        System.out.println("A) Create Account");
        System.out.println("B) Deposit to an Account");
        System.out.println("C) Withdraw from an Account");
        System.out.println("E) Change Password");
        System.out.println("L) Log Out");
        System.out.println("====================================================");
        System.out.print("Choice: ");
        char ch = sc.next().charAt(0);
        ch = Character.toLowerCase(ch);
        switch (ch) {
            case 'a':
                Account newacc = banker.createAnAccount(accountlist);
                accountlist.add(newacc);
                employee();
                break;
            case 'b':
                System.out.print("Enter the account number: ");
                long a = sc.nextLong();
                System.out.print("Enter the amount to deposit: ");
                double depo = sc.nextDouble();
                for (Account ac : accountlist) {
                    if (ac.getNumber() == a) {
                        banker.deposit(depo, ac);
                        employee();
                        ;
                    }
                }
                System.out.println("Account not found, press Enter to return. ");
                sc.nextLine();
                employee();
                ;
                break;
            case 'c':
                System.out.print("Enter the account number: ");
                long b = sc.nextLong();
                System.out.print("Enter the amount to debit: ");
                double deb = sc.nextDouble();
                for (Account ac : accountlist) {
                    if (ac.getNumber() == b) {
                        banker.debit(deb, ac);
                        employee();
                        ;
                    }
                }
                System.out.println("Account not found, press Enter to return. ");
                sc.nextLine();
                employee();
                ;
                break;
            case 'e':
                banker.changePassword();
                employee();
                break;
            case 'l':
                logout();
                break;

        }

    }

    public void check() {
        while (usertype == 'd') {
            // usertype = login();
        }
        if (usertype == 'a') {
            admin();
        }
        if (usertype == 'b') {
            employee();
        }
        if (usertype == 'c') {
            customer();
        }
    }

    TextField username = new TextField();
    PasswordField passowrd = new PasswordField();
    TextField a = new TextField();
    TextField b = new TextField();
    PasswordField oldPW = new PasswordField();
    PasswordField newPW = new PasswordField();
    PasswordField confirmPW = new PasswordField();

    Button logbt = new Button("Log in");
    static Stage mainStage = new Stage();

    public void start(Stage primaryStage) {
        this.mainStage = primaryStage;
        mainstage();
    }

    public void mainstage() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setAlignment(Pos.CENTER);
        pane.add(new Label("Account Number: "), 0, 0);
        pane.add(username, 1, 0);
        pane.add(new Label("Password: "), 0, 1);
        pane.add(passowrd, 1, 1);
        pane.add(logbt, 1, 2);
        Login login = new Login();
        logbt.setOnAction(login);
        Scene scene1 = new Scene(pane, 400, 400, Color.AQUA);
        mainStage.setX(100);
        mainStage.setY(50);
       Image icon = new Image("icon.jpg");
        mainStage.getIcons().add(icon);
        mainStage.setTitle("Online Banking");
        mainStage.setScene(scene1);
        mainStage.show();
    }

    public void logout() {
        account = null;
        banker = null;
        username.setText("");
        passowrd.setText("");
        mainstage();
    }

    class Login implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            long num;
            String pass;
            num = exception.correctInputLong(username);
            if (num == 0)
                return;
            pass = passowrd.getText();
            if (num == -1) {
                if (pass.equals(admipass)) {
                    admin();
                }
            }
            for (Banker b : bankerlist) {
                if (num == b.getId()) {
                    if (pass.equals(b.getPassword())) {
                        banker = b;
                        employee();
                    }
                }
            }
            for (Account a : accountlist) {
                if (num == a.getNumber()) {
                    if (pass.equals(a.getPassword())) {
                        account = a;
                        customer();
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Invalid");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Username or Password");
            alert.showAndWait();
            passowrd.setText("");
            username.setText("");
        }
    }

    public void customer() {
        VBox root = new VBox();
        GridPane pane = new GridPane();
        // pane.setAlignment(Pos.BOTTOM_LEFT);
        pane.setHgap(30);
        pane.setPadding(new Insets(0, 20, 5, 20));
        Button bt1 = new Button("Transfer");
        Button bt2 = new Button("Request Transaction");
        Button bt3 = new Button("Ask Question");
        Button bt4 = new Button("Change Password");
        Button bt5 = new Button("Log Out");
        Button bt6 = new Button("Manage Contact");
        Button bt7 = new Button("Notifications");
        Label name = new Label("Name: " + account.getOwner().getName().first_name + " "
                + account.getOwner().getName().middle_name + " " + account.getOwner().getName().last_name + " ");
        Label num = new Label("Account Number: " + account.getNumber());
        Label amount = new Label("Amount: " + account.getAmount());
        VBox v = new VBox();
        v.setPadding(new Insets(10, 10, 10, 10));
        v.getChildren().addAll(name, num, amount);
        pane.add(bt1, 0, 0);
        pane.add(bt6, 1, 0);
        pane.add(bt2, 2, 0);
        pane.add(bt7, 3, 0);
        pane.add(bt3, 4, 0);
        pane.add(bt4, 5, 0);
        pane.add(bt5, 6, 0);
        pane.add(v, 7, 0);
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(60);
        l.setEndX(830);
        l.setEndY(60);
        l.setStroke(Color.GOLD);
        l.setStrokeWidth(3);
        root.getChildren().add(pane);
        root.getChildren().add(l);

        // Transfer
        Label c = new Label("Enter the Amount");
        Label d = new Label("Enter the Account Number");
        Button tran = new Button("Transfer");
        GridPane transfer = new GridPane();
        transfer.setPadding(new Insets(20, 0, 0, 20));
        transfer.setHgap(15);
        transfer.setVgap(10);
        transfer.add(c, 0, 0);
        transfer.add(a, 1, 0);
        transfer.add(d, 0, 1);
        transfer.add(b, 1, 1);
        transfer.add(tran, 1, 2);
        bt1.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, transfer), 1100, 700)));
        Transfer t = new Transfer();
        tran.setOnAction(t);
        // Transfer

        // Contacts
        // Contacts
        //Ask Question
        // Notification
        ArrayList<Label> nt = new ArrayList<>();
        VBox notf = new VBox();
        int i = 0;
        for (String not : account.getNotification()) {
            nt.add(new Label(not));
            notf.getChildren().add(nt.get(0));
            i++;
        }
        bt7.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, notf), 1100, 700)));
        // Notification

        // Change Password
        GridPane change = new GridPane();
        change.setPadding(new Insets(50, 50, 50, 50));
        change.setVgap(20);
        change.setHgap(10);
        change.add(new Label("Enter old Password: "), 0, 0);
        change.add(oldPW, 1, 0);
        change.add(new Label("Enter new Password: "), 0, 1);
        change.add(newPW, 1, 1);
        change.add(new Label("Confirm new Password: "), 0, 2);
        change.add(confirmPW, 1, 2);
        Button ch = new Button("Change");
        change.add(ch, 1, 3);
        ChangePW chang = new ChangePW();
        ch.setOnAction(chang);
        bt4.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, change), 1100, 700)));
        // Change Password

        // Logout
        bt5.setOnAction(e -> logout());
        // logout

        Scene scene2 = new Scene(root, 1100, 700);
        mainStage.alwaysOnTopProperty();
        mainStage.setScene(scene2);
        mainStage.show();

    }

    class Transfer implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            long receiver = exception.correctInputLong(a);
            double amount = exception.correctInputDouble(b);
            if (amount == 0 || receiver == 0)
                return;
            String info = account.send(receiver, amount, accountlist);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(info);
            alert.showAndWait();
            a.setText("");
            b.setText("");
            customer();
        }
    }

    class ChangePW implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String old = oldPW.getText();
            String newpw = newPW.getText();
            String confirm = confirmPW.getText();
            String info = account.changePassword(old, newpw, confirm);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(info);
            alert.showAndWait();
            oldPW.setText("");
            newPW.setText("");
            confirmPW.setText("");
            customer();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Person c = new Person();
        // Name n = new Name();
        /*
         * n.first_name = "a";
         * n.middle_name = "a";
         * n.last_name = "a";
         */
        /*
         * c.setName(n);
         * Account t = new Account("xxx", c);
         * t.setAmount(2000.56);
         *
         * Banker b = new Banker();
         * // b.setFull_name(n);
         * ;
         */
        // bankerlist.add(banker);
        /*
         * accountlist.add(t);
         * Name m = new Name();
         * Person d = new Person();
         * m.first_name = "b";
         * m.middle_name = "b";
         * m.last_name = "b";
         * d.setName(m);
         */
        Account w = new Account(1);
        Account x = new Account(1);
        accountlist.add(w);
        accountlist.add(x);

        /*
         * w.setAmount(30000.56);
         * accountlist.add(w);
         */
        // check();
        launch(args);
    }
}
