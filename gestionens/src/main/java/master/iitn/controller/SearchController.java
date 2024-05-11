package master.iitn.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import master.iitn.dao.AdministrateurDao;
import master.iitn.dao.SearchingDao;
import master.iitn.model.Etudiant;
import master.iitn.model.User;

public class SearchController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the ToggleGroup for search index
        SearchIndex = new ToggleGroup();
        RBtnSearchByCin.setToggleGroup(SearchIndex);
        RBtnSearchByCNE.setToggleGroup(SearchIndex);
        RBtnSearchByClasse.setToggleGroup(SearchIndex);
    }

    // Search Student Fields
    @FXML
    private TextField SearchStudentField;

    @FXML
    private ToggleGroup SearchIndex;
    @FXML
    private RadioButton RBtnSearchByCin;
    @FXML
    private RadioButton RBtnSearchByCNE;
    @FXML
    private RadioButton RBtnSearchByClasse;

    // Table Columns

    @FXML
    private TableView<Etudiant> TableStudent;
    @FXML
    private TableColumn<User, Integer> ID_Etudiant;
    @FXML
    private TableColumn<User, String> Nom;
    @FXML
    private TableColumn<User, String> Prenom;
    @FXML
    private TableColumn<User, String> CIN;
    @FXML
    private TableColumn<User, String> CNE;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, String> Classe;


    AdministrateurDao administateurDao = new AdministrateurDao();
    SearchingDao searchingDao = new SearchingDao();

    public String getSelectedSeachIndex() {
        if (SearchIndex.getSelectedToggle() == RBtnSearchByCin) {
            return "CIN";
        } else if (SearchIndex.getSelectedToggle() == RBtnSearchByCNE) {
            return "CNE";
        } else if (SearchIndex.getSelectedToggle() == RBtnSearchByClasse) {
            return "Classe";
        }
        return null;
    }

    @FXML
    public void FillUpUsersTable(ActionEvent event) {
        // Bind the table columns to the properties of the User class
        ID_Etudiant.setCellValueFactory(new PropertyValueFactory<>("id")); // Update to match the property name in User
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        CIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
        CNE.setCellValueFactory(new PropertyValueFactory<>("cne"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Classe.setCellValueFactory(new PropertyValueFactory<>("Classe"));
        // Fetch all User objects from the database based on search criteria
        ObservableList<Etudiant> etudiants = searchingDao.searchEtudiant(getSelectedSeachIndex(),
                SearchStudentField.getText());

        // Set the items of the TableView
        TableStudent.setItems(etudiants);
    }

    @FXML
    public void SearchStudent(ActionEvent event) throws IOException {
        try {
            System.out.println(getSelectedSeachIndex() + "" + SearchStudentField.getText());
            searchingDao.searchEtudiant(getSelectedSeachIndex(), SearchStudentField.getText());
            FillUpUsersTable(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
