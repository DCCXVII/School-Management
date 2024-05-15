package master.iitn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import master.iitn.model.User;

import java.io.IOException;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD
        scene = new Scene(loadFXML("view/Admin/AdminImprimer"), 1280, 720);
=======
        scene = new Scene(loadFXML("view/Prof/MainProf"), 1280, 720);
>>>>>>> 31d01d05bd612a6e38793bc812e41fba97f0ab87
        stage.setScene(scene);
        stage.setTitle("School Management");
        stage.show();
    }

    public static void setRoot(String fxml, User user) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}