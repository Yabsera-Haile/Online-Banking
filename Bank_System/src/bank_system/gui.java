package bank_system;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class gui extends Application {
       String f = "a", m = "b", l = "c";
       long an = 12345;

       public void start(Stage primaryStage) {
              /*
               * Label label1 = new Label();
               * Label label2 = new Label();
               * label1.setText("Name: ");
               * label2.setText(f + " " + m + " " + l);
               * HBox h1 = new HBox();
               * h1.getChildren().addAll(label1, label2);
               * Label label3 = new Label();
               * Label label4 = new Label();
               * label3.setText("Account Number: ");
               * label4.setText(an + " ");
               * HBox h2 = new HBox();
               * h2.getChildren().addAll(label3, label4);
               * VBox v1 = new VBox();
               * v1.getChildren().addAll(h1, h2);
               * Scene sc = new Scene(v1, 500, 300);
               * primaryStage.setTitle("Home Page");
               * primaryStage.setScene(sc);
               * primaryStage.show();
               */

              Label label1 = new Label();
              TextField label2 = new TextField();
              label1.setText("Account Number: ");
              HBox h1 = new HBox();
              h1.setAlignment(Pos.CENTER);
              h1.getChildren().addAll(label1, label2);
              Label label3 = new Label();
              TextField label4 = new TextField();
              label3.setText("Password: ");
              HBox h2 = new HBox();
              h2.setAlignment(Pos.CENTER);
              h2.getChildren().addAll(label3, label4);
              Button b = new Button();
              b.setText("Sign in");
              HBox h3 = new HBox();
              h3.setAlignment(Pos.CENTER);
              h3.getChildren().add(b);
              VBox v1 = new VBox();
              v1.setAlignment(Pos.CENTER);

              v1.getChildren().addAll(h1, h2, h3);
       //       Group root = new Group(v1);
       
              Scene sc = new Scene(v1, 600, 600);
              primaryStage.setTitle("Login Page");
              primaryStage.setScene(sc);
              primaryStage.show();

       }

       public static void main(String[] args) {

              launch(args);
       }
}
