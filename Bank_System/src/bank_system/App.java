package bank_system;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.time.LocalDate;

public class App extends Application {
    private static ArrayList<Account> accountlist = new ArrayList<>();
    private static ArrayList<Banker> bankerlist = new ArrayList<>();
    private static String admipass = "Password";
    private static Account account;
    private static Banker banker;
    private static char usertype = 'd';
    MyException exception = new MyException();

    public void admin() {
        System.out.print("\033[H\033[2J");
        System.out.println("Welcome Admin!");
    }

    public void mployee() {
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
                // banker.changePassword();
                employee();
                break;
            case 'l':
                logout();
                break;

        }

    }

    TextField username = new TextField();
    PasswordField passowrd = new PasswordField();
    TextField ac_num = new TextField();
    TextField am = new TextField();
    PasswordField oldPW = new PasswordField();
    PasswordField newPW = new PasswordField();
    PasswordField confirmPW = new PasswordField();
    TextField question = new TextField();
    TextField rAm = new TextField();
    TextField fname = new TextField();
    TextField fnum = new TextField();
    TextField fdel = new TextField();

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Logout Confirm");
        alert.setHeaderText("Confirm Logout");
        alert.setContentText("Are you sure you want to Logout.");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            account = null;
            banker = null;
            username.setText("");
            passowrd.setText("");
            mainstage();
        }

    }

    class Login implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int i = 0;
            long num;
            String pass;
            num = exception.correctInputLong(username);
            if (num == 0)
                return;
            pass = passowrd.getText();
            if (num == -1) {
                if (pass.equals(admipass)) {
                    i++;
                    admin();
                }
            }
            for (Banker b : bankerlist) {
                if (num == b.getId()) {
                    if (pass.equals(b.getPassword())) {
                        banker = b;
                        i++;
                        employee();

                    }
                }
            }
            for (Account a : accountlist) {
                if (num == a.getNumber()) {
                    if (pass.equals(a.getPassword())) {
                        account = a;
                        i++;
                        customer();
                    }
                }
            }
            if (i == 0) {
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
    }

    public void customer() {
        VBox root = new VBox();
        GridPane pane = new GridPane();
        pane.setHgap(30);
        pane.setPadding(new Insets(0, 20, 5, 20));
        Button bt1 = new Button("Transfer");
        Button bt2 = new Button("Request Transaction");
        Button bt3 = new Button("Ask Question");
        Button bt4 = new Button("Change Password");
        Button bt5 = new Button("Log Out");
        Button bt6 = new Button("Manage Contact");
        Button bt7 = new Button("Notifications(" + account.getNotification().size() + ")");
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
        Button choose = new Button("Choose from Contact");
        Button cont = new Button("Transfer");
        GridPane transfer = new GridPane();
        transfer.setPadding(new Insets(20, 0, 0, 20));
        transfer.setHgap(15);
        transfer.setVgap(10);
        transfer.add(c, 0, 0);
        transfer.add(am, 1, 0);
        transfer.add(d, 0, 1);
        transfer.add(ac_num, 1, 1);
        transfer.add(tran, 1, 2);
        transfer.add(choose, 0, 5);
        bt1.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, transfer), 1100, 700)));
        Transfer t = new Transfer();
        tran.setOnAction(t);
        // Transfer

        // Contacts
        TableView<Beneficairy> friends = new TableView<>();
        TableColumn<Beneficairy, String> nColumn = new TableColumn<>("Name");
        nColumn.setCellValueFactory(new PropertyValueFactory<Beneficairy, String>("name"));
        TableColumn<Beneficairy, Long> lColumn = new TableColumn<>("Account Number");
        lColumn.setCellValueFactory(new PropertyValueFactory<Beneficairy, Long>("accountno"));
        friends.getColumns().addAll(nColumn, lColumn);
        friends.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        friends.setPlaceholder(new Label("Contact List is Empty"));
        friends.getItems().addAll(account.getFriends());
        friends.setMaxWidth(500);
        lColumn.setSortType(TableColumn.SortType.ASCENDING);
        HBox h = new HBox();
        h.setPadding(new Insets(10, 10, 10, 10));
        Button addC = new Button("Add Contact");
        addFriend aFriend = new addFriend();
        addC.setOnAction(aFriend);
        Button delC = new Button("Delete Contact");
        h.getChildren().addAll(new Label("    Enter Name: "), fname,
                new Label("    Enter Account Number: "), fnum, addC);
        VBox vb = new VBox();
        vb.getChildren().addAll(h, friends, delC);
        delC.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Confirm Delete");
                alert.setHeaderText("Confirm Delete");
                alert.setContentText("Are you sure you want to delete.");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    ObservableList<Beneficairy> select, selected;
                    select = friends.getItems();
                    selected = friends.getSelectionModel().getSelectedItems();
                    account.deleteFriends(selected.get(0));
                    selected.forEach(select::remove);
                    customer();
                }
            }
        });
        bt6.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, vb), 1100, 700)));

        // Transfer from Contacts
        TextField amo = new TextField();
        HBox cBox = new HBox();
        cBox.getChildren().addAll(new Label("Enter the Amount:    "), amo);
        choose.setOnAction(e -> mainStage.setScene(
                new Scene(new VBox(pane, l, cBox, new Label("Select the Reciever then click Transfer"), friends, cont),
                        1100, 700)));
        cont.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ObservableList<Beneficairy> select, selected;
                select = friends.getItems();
                selected = friends.getSelectionModel().getSelectedItems();
                if (exception.correctInputDouble(amo) == 0) {
                    return;
                }
                account.send(selected.get(0).getAccountno(), exception.correctInputDouble(amo), accountlist);
                customer();
            }
        });
        // Transfer from Contacts

        // Contacts

        // Get Request
        ComboBox<String> type = new ComboBox<>();
        type.getItems().addAll("Withdrawal", "Deposit", "Loan");
        Label rAmout = new Label("Amount: ");
        Label ty = new Label("Type: ");
        GridPane request = new GridPane();
        request.setVgap(20);
        request.setVgap(20);
        Button send = new Button("Send Request");
        request.setVgap(20);
        request.setHgap(10);
        request.setPadding(new Insets(50, 50, 50, 50));
        request.add(ty, 0, 0);
        request.add(type, 1, 0);
        request.add(rAmout, 0, 1);
        request.add(rAm, 1, 1);
        request.add(send, 3, 2);
        bt2.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, request), 1100, 700)));
        // Get Request

        // Ask Question
        Label f = new Label("Enter Your Question:");
        GridPane quest = new GridPane();
        quest.setPadding(new Insets(50, 50, 50, 50));
        quest.setHgap(20);
        Button sub = new Button("Submit");
        question.setMinWidth(500);
        question.setMinHeight(50);
        quest.add(f, 0, 0);
        quest.add(question, 1, 0);
        quest.add(sub, 1, 1);
        bt3.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, quest), 1100, 700)));
        QandA qandA = new QandA();
        sub.setOnAction(qandA);
        // Ask Question

        // Notification
        ArrayList<Label> nt = new ArrayList<>();
        VBox notf = new VBox();
        for (String not : account.getNotification()) {
            nt.add(new Label(not));
        }
        for (int i = 0; i < nt.size(); i++) {
            notf.getChildren().add(nt.get(i));
        }
        Button seen = new Button("Seen");
        notf.getChildren().add(seen);
        bt7.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, notf), 1100, 700)));
        seen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                account.seenNotification();
                customer();
            }
        });
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
            long receiver = exception.correctInputLong(ac_num);
            double amount = exception.correctInputDouble(am);
            if (amount == 0 || receiver == 0)
                return;
            String info = account.send(receiver, amount, accountlist);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(info);
            alert.showAndWait();
            am.setText("");
            ac_num.setText("");
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

    class QandA implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String ques = question.getText();
            AnswerQuestion a = account.askQuetion(ques);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Your question has been recieved, It will answered shortly.");
            alert.showAndWait();
            question.setText("");
            customer();
        }
    }

    class addFriend implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            account.addFriends(fname.getText(), exception.correctInputLong(fnum), accountlist);
            fname.setText("");
            fnum.setText("");
            customer();
        }
    }

    public void employee() {
        VBox root = new VBox();
        GridPane pane = new GridPane();
        pane.setHgap(30);
        pane.setPadding(new Insets(0, 20, 5, 20));
        Button bt1 = new Button("Create New Account");
        Button bt2 = new Button("View Normal Request");
        Button bt3 = new Button("View Loan Request");
        Button bt4 = new Button("Search Transaction");
        Button bt5 = new Button("Search Accounts");
        Button bt6 = new Button("Answer Question");
        Button bt7 = new Button("Change Password");
        Button bt8 = new Button("Log Out");
        Label name = new Label("Name: " + banker.getName().first_name + " "
                + banker.getName().middle_name + " " + banker.getName().last_name + " ");
        Label num = new Label("Id Number: " + banker.getId());
        VBox v = new VBox();
        v.setPadding(new Insets(10, 10, 10, 10));
        v.getChildren().addAll(name, num);
        pane.add(bt1, 0, 0);
        pane.add(bt2, 1, 0);
        pane.add(bt3, 2, 0);
        pane.add(bt4, 3, 0);
        pane.add(bt5, 4, 0);
        pane.add(bt6, 5, 0);
        pane.add(bt7, 6, 0);
        pane.add(bt8, 7, 0);
        pane.add(v, 8, 0);
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(60);
        l.setEndX(1100);
        l.setEndY(60);
        l.setStroke(Color.GOLD);
        l.setStrokeWidth(3);
        root.getChildren().add(pane);
        root.getChildren().add(l);

        // Create Account
        GridPane register = new GridPane();
        register.setPadding(new Insets(80, 80, 80, 80));
        register.setHgap(10);
        register.setVgap(20);
        register.add(new Label("First Name:"), 0, 0);
        TextField registerFname = new TextField();
        register.add(registerFname, 1, 0);
        register.add(new Label("Middle Name:"), 0, 1);
        TextField registerMname = new TextField();
        register.add(registerMname, 1, 1);
        register.add(new Label("Last Name:"), 0, 2);
        TextField registerLname = new TextField();
        register.add(registerLname, 1, 2);
        register.add(new Label("Date of Birth:"), 0, 3);
        DatePicker datePicker = new DatePicker();
        register.add(datePicker, 1, 3);
        register.add(new Label("Sex:"), 0, 4);
        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("Female", "Male");
        register.add(gender, 1, 4);
        register.add(new Label("Type of Account:"), 0, 5);
        ComboBox<String> registerType = new ComboBox<>();
        registerType.getItems().addAll("Saving", "Checking", "Non-Interest");
        register.add(registerType, 1, 5);
        register.add(new Label("Telephone Number:"), 0, 6);
        TextField registerTel = new TextField();
        register.add(registerTel, 1, 6);
        register.add(new Label("Email Address:"), 0, 7);
        TextField registerMail = new TextField();
        register.add(registerMail, 1, 7);
        register.add(new Label("Initial Amount:"), 0, 8);
        TextField intialAmount = new TextField();
        register.add(intialAmount, 1, 8);
        Button create = new Button("Create Account");
        create.setMinWidth(100);
        create.setMinHeight(30);
        register.add(create, 3, 10);
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (registerFname.getText() == "" || registerMname.getText() == "" || registerLname.getText() == "" ||
                        datePicker.getValue() == null || gender.getValue() == null || registerMail.getText() == ""
                        || registerTel.getText() == "" || registerFname.getText() == ""
                        || registerType.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Empty Field");
                    alert.setHeaderText(null);
                    alert.setContentText("Fill all Blanks.");
                    alert.showAndWait();
                    return;
                }
                double registerAmount = exception.correctInputDouble(intialAmount);
                if (registerAmount == 0) {
                    return;
                }
                Account newAccount = new Account();
                LocalDate dob = datePicker.getValue();
                newAccount.setAccount(registerFname.getText(),
                        registerMname.getText(), registerLname.getText(),
                        dob.getDayOfMonth(), dob.getMonthValue(), dob.getYear(), gender.getValue(),
                        registerMail.getText(), registerTel.getText(), registerType.getValue(), registerAmount);
                accountlist.add(newAccount);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Account Has been Created the Account number is " + newAccount.getNumber()
                        + " and password by default is 1234.");
                alert.showAndWait();
                employee();
            }
        });
        bt1.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, register), 1300, 700)));
        // Create Account

       
        //View Normal Request
        TableView<Request> nRequest = new TableView<>();
        TableColumn<Request, String> tColumn = new TableColumn<>("Type");
        tColumn.setCellValueFactory(new PropertyValueFactory<Request, String>("type"));
        TableColumn<Request, Long> ANColumn = new TableColumn<>("Account Number");
        ANColumn.setCellValueFactory(new PropertyValueFactory<Request, Long>("requester.getNumber()"));
        TableColumn<Request, Double> AmColumn = new TableColumn<>("Ammount");
        AmColumn.setCellValueFactory(new PropertyValueFactory<Request, Double>("amount"));
        nRequest.getColumns().addAll(tColumn,ANColumn, AmColumn);
        nRequest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nRequest.setPlaceholder(new Label("No Request Available"));
        nRequest.getItems().addAll(banker.getRequests());
        nRequest.setMaxWidth(500);
        ANColumn.setSortType(TableColumn.SortType.ASCENDING);
        bt2.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, nRequest), 1300, 700)));
        Button approve=new Button("Approve");
        Button discard = new Button("Discard");
        

        // View Normal Request


        // Change Password
        GridPane changePW = new GridPane();
        changePW.setPadding(new Insets(50, 50, 50, 50));
        changePW.setVgap(20);
        changePW.setHgap(10);
        changePW.add(new Label("Enter old Password: "), 0, 0);
        PasswordField oldPW = new PasswordField();
        changePW.add(oldPW, 1, 0);
        changePW.add(new Label("Enter new Password: "), 0, 1);
        PasswordField newPW = new PasswordField();
        changePW.add(newPW, 1, 1);
        changePW.add(new Label("Confirm new Password: "), 0, 2);
        PasswordField confirmPW = new PasswordField();
        changePW.add(confirmPW, 1, 2);
        Button chPW = new Button("Change Passowrd");
        changePW.add(chPW, 1, 3);
        chPW.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String info = banker.changePassword(oldPW.getText(), newPW.getText(), confirmPW.getText());
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
        });
        bt7.setOnAction(e -> mainStage.setScene(new Scene(new VBox(pane, l, changePW), 1300, 700)));
        // Change Password

        // Logout
        bt8.setOnAction(e -> logout());
        // Logout
        Scene scene2 = new Scene(root, 1300, 700);
        mainStage.alwaysOnTopProperty();
        mainStage.setScene(scene2);
        mainStage.show();

    }

    public static void main(String[] args) {
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
        Banker a = new Banker(1);
        Banker b = new Banker(1);
        bankerlist.add(a);
        bankerlist.add(b);
        /*
         * w.setAmount(30000.56);
         * accountlist.add(w);
         */
        // check();
        launch(args);
    }
}
