package master.iitn.controller.Prof;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
    @FXML
    BorderPane MainBox;

    @Override
    public void initialize(URL location, java.util.ResourceBundle resources) {
        goToGestionNotes();
    }

    @FXML
    public void goToGestionNotes() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Prof/ProfGestionEtudiant.fxml");
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToProfile() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Prof/ProfProfile.fxml");
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}