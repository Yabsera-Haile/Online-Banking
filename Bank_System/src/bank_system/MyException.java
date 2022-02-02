package bank_system;

import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class MyException extends Exception {

    public long correctInputLong(TextField test) {
        long reply;
        try {
            reply = Long.parseLong(test.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Empty Field");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Correct Number ");
            alert.showAndWait();
            return 0;
        }
        return reply;
    }

    public double correctInputDouble(TextField test) {
        double reply;
        try {
            reply = Double.parseDouble(test.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Empty Field");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Correct Number ");
            alert.showAndWait();
            return 0;
        }
        return reply;
    }
}
