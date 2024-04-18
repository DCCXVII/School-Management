package master.iitn;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {

    // this is just a for fun example
    @FXML
    private Label hello;

    @FXML
    private TextField digitOne;

    @FXML
    private TextField digitTwo;

    @FXML
    private Label result;

    @FXML
    private void wrapOnMe(Event event) {
        hello.setScaleX(1.5);
        hello.setScaleY(2);
    }

    @FXML
    private void wrapOutMe(Event event) {
        hello.setScaleX(1);
        hello.setScaleY(1);
    }

    @FXML
    private void calculate(ActionEvent event) throws IOException {
        try {
            int a = Integer.parseInt(digitOne.getText());
            int b = Integer.parseInt(digitTwo.getText());
            result.setText("Result = " + String.valueOf(a + b));
        } catch (NumberFormatException e) {
            result.setText("Invalid inputs");
        }
    }

    // necessary to switch to another view
    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        App.setRoot("view/secondary");
    }

    @FXML
    private void switchToScene(ActionEvent event) throws IOException {
        App.setRoot("view/scene");
    }
}
