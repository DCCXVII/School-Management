package master.iitn.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import master.iitn.model.Matiere;
import master.iitn.dao.MatiereDao;

public class MatiereController implements Initializable{

    MatiereDao matiereDao = new MatiereDao();

    @FXML
    private ComboBox ComboClasse;
    @FXML
    private ComboBox ComboModule;

    @FXML
    private ComboBox ComboProfesseur;

    @FXML
    private TextField TextField_AddMatiere;

    @FXML
    private TableView<Matiere> TableMatiere;

    // @FXML
    // private TableColumn<Matiere, Integer> IdMatiere;
    @FXML
    private TableColumn<Matiere, String> Col_Classe;
    @FXML
    private TableColumn<Matiere, String> Col_Module;
    @FXML
    private TableColumn<Matiere, String> Col_NomMatiere;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        FillMatieres(null);

        ComboClasse.getItems().addAll(matiereDao.getClassNames());
        ComboModule.getItems().addAll(matiereDao.getModulesNames());
        ComboProfesseur.getItems().addAll(matiereDao.getProfesseurNames());

    }

    

    @FXML
    private void addNewMatiere(ActionEvent event) throws IOException {
        try {
            String nom_matiere = TextField_AddMatiere.getText().toString();
            int id_class = Integer.parseInt(ComboClasse.getValue().toString().split(" ")[0]);
            int id_module = Integer.parseInt(ComboModule.getValue().toString().split(" ")[0]);
            int id_professeur = Integer.parseInt(ComboProfesseur.getValue().toString().split(" ")[0]);

            Matiere mat = new Matiere(nom_matiere);

            System.out.println(mat.getNomMatiere());

            matiereDao.addMatiere(mat, id_module, id_class, id_professeur);
            FillMatieres(event);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    public void FillMatieres(ActionEvent event) {
        // Bind the table columns to the properties of the Matiere class
        // IdMatiere.setCellValueFactory(new PropertyValueFactory<>("id"));
        Col_Classe.setCellValueFactory(new PropertyValueFactory<>("nomClass"));
        Col_Module.setCellValueFactory(new PropertyValueFactory<>("nomModule"));
        Col_NomMatiere.setCellValueFactory(new PropertyValueFactory<>("nomMatiere"));

        // Fetch all Matiere objects from the database
        ObservableList<Matiere> matieres = matiereDao.getAllMatieres();

        // Set the items of the TableView
        TableMatiere.setItems(matieres);
    }
}
