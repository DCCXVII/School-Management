package master.iitn.controller.Admin;

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
        goToAddEtudiant();
    }

    @FXML
    public void goToGestionMatiere() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Admin/AdminMatieres.fxml");
            System.out.println(url);
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }
        } catch (java.io.IOException e) {
            e.getStackTrace();
        }

    }

    @FXML
    public void goToImpression() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Admin/AdminImprimer.fxml");
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }
        } catch (java.io.IOException e) {
            e.getStackTrace();
        }

    }

    @FXML
    public void goToAddEtudiant() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Admin/AdminGestionEtudiant.fxml");
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @FXML
    public void goToSearchEtudiant() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Admin/AdminGestionEtudiant1.fxml");
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    @FXML
    public void goToProfile() {
        try {
            URL url = getClass().getClassLoader().getResource("master/iitn/view/Admin/AdminProfile.fxml");
            if (url != null) {
                FXMLLoader loader = new FXMLLoader(url);
                BorderPane pane1 = loader.load();
                MainBox.setCenter(pane1);
            } else {
                System.out.println("FXML file not found.");
            }
        } catch (java.io.IOException e) {
            e.getStackTrace();
        }

    }

}
