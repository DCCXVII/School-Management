package master.iitn.controller.Etudiant;

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
        goesToNotes();
    }

    @FXML
    public void goesToNotes() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Etudiant/EtudiantProfile.fxml");
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
    public void goesToProfile() {

        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Etudiant/EtudiantShowNotes.fxml");
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

    };

}
