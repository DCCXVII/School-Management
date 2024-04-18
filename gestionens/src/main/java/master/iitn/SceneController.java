package master.iitn;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SceneController {

    @FXML
    private TextField name;

    @FXML
    private Label hello;

    @FXML
    void onBtnClick(ActionEvent event) throws IOException{
        try {
            String name = this.name.getText();
            hello.setText("Hello " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
