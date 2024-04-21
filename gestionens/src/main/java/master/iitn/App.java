package master.iitn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import master.iitn.dao.ConnectDB;
import master.iitn.dao.ConnectionProps;
import service.DatabasePropertiesLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view/Admin"), 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Hello world");
        // ConnectDB connectDB ;
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();
        System.out.println(connectDB.getConnection());
        launch();
    }

}