package master.iitn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import master.iitn.dao.ConnectionFactory;
import master.iitn.dao.MatiereDao;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view/GestionDeMatiere"), 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Hello world");
        // ConnectDB connectDB ;
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // ConnectionFactory connectDB = new ConnectionFactory();
        // System.out.println(connectDB.getConnection());

        MatiereDao matiereDao = new MatiereDao();
        
           matiereDao.getAllMatieres().forEach(matiere -> {
               System.out.println(matiere.getId() + " " + matiere.getNomMatiere());
           });
        
        launch();
    }

}