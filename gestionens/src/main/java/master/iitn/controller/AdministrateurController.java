package master.iitn.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import Utils.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import master.iitn.dao.AdministrateurDao;
import master.iitn.dao.MatiereDao;
import master.iitn.dao.SearchingDao;
import master.iitn.model.Etudiant;
import master.iitn.model.Gender;
import master.iitn.model.Matiere;
import master.iitn.model.Roles;
import master.iitn.model.User;

public class AdministrateurController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the ToggleGroup for gender
        // Genre = new ToggleGroup();
        // RBtn_MaleStudent.setToggleGroup(Genre);
        // RBtn_FemaleStudent.setToggleGroup(Genre);

        // Initialize the ToggleGroup for search index
        SearchIndex = new ToggleGroup();
        RBtnSearchByCin.setToggleGroup(SearchIndex);
        RBtnSearchByCNE.setToggleGroup(SearchIndex);
        RBtnSearchByClasse.setToggleGroup(SearchIndex);
    }

    // Gender Section
    @FXML
    private ToggleGroup Genre;
    @FXML
    private RadioButton RBtn_MaleStudent;
    @FXML
    private RadioButton RBtn_FemaleStudent;

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

    // Add Matiere Fields
    @FXML
    private TextField TextField_AddMatiere;
    @FXML
    private TableView<Matiere> TableMatiere;
    @FXML
    private TableColumn<Matiere, Integer> IdMatiere;
    @FXML
    private TableColumn<Matiere, String> NomMatiere;

    // Add Student Fields
    @FXML
    private TextField NomEtudiant;
    @FXML
    private TextField PrenomEtudiant;
    @FXML
    private TextField EmailEtudiant;
    @FXML
    private TextField PasswordEtudiant;
    @FXML
    private TextField PhoneEtudiant;
    @FXML
    private TextField CinEtudiant;
    @FXML
    private TextField CneEtudiant;
    @FXML
    private TextField LevelEtudiant;

    @FXML
    private TextField ClassEtudiant;
    @FXML
    private DatePicker DateNaissanceEtudiant;
    @FXML
    private Button AddEtudiant;
    @FXML
    private Button Cancel;

    // DAOs and Utils
    MatiereDao matiereDao = new MatiereDao();
    Utils utils = new Utils();
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

    public String getSelectedGender() {
        if (RBtn_MaleStudent.isSelected()) {
            return "Homme";
        } else if (RBtn_FemaleStudent.isSelected()) {
            return "Femme";
        }
        return null;
    }

    @FXML
    private void addNewUser(ActionEvent event) throws IOException {
        try {
            String nom = NomEtudiant.getText();
            String prenom = PrenomEtudiant.getText();
            String email = utils.generateEmail(nom, prenom);
            String pass = nom;
            String tele = PhoneEtudiant.getText();
            String cin = CinEtudiant.getText();
            String cne = CneEtudiant.getText();
            String level = LevelEtudiant.getText();
            String Class = ClassEtudiant.getText();
            // Check if a date has been selected in the DatePicker
            LocalDate selectedDate = DateNaissanceEtudiant.getValue();
            Date dob = (selectedDate != null) ? java.sql.Date.valueOf(selectedDate) : null;

            String image = "default";
            Roles role = Roles.Etudiant;
            Gender gender = getSelectedGender().equals("Homme") ? Gender.Homme : Gender.Femme;

            Etudiant etudiant = new Etudiant(0, image, nom, prenom, email, pass, role, cin, cne, tele, gender, dob,
                    level, Class);
            System.out.println(etudiant.toString());
            administateurDao.Addetudiant(etudiant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addNewMatiere(ActionEvent event) throws IOException {
        try {
            String nom_matiere = TextField_AddMatiere.getText();

            Matiere mat = new Matiere(nom_matiere);

            System.out.println(mat.getNomMatiere());

            matiereDao.addMatiere(mat);
            FillMatieres(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void FillMatieres(ActionEvent event) {
        // Bind the tabale columns to the properties of the Matiere class
        IdMatiere.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomMatiere.setCellValueFactory(new PropertyValueFactory<>("nomMatiere"));

        // Fetch all Matiere objects from the database
        ObservableList<Matiere> matieres = matiereDao.getAllMatieres();

        // Set the items of the TableView
        TableMatiere.setItems(matieres);
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
        Classe.setCellValueFactory(new PropertyValueFactory<>("nomClass"));
        // Fetch all User objects from the database based on search criteria
        ObservableList<Etudiant> etudiants = searchingDao.SearchEtudiant(getSelectedSeachIndex(),
                SearchStudentField.getText());

        // Set the items of the TableView
        TableStudent.setItems(etudiants);
    }

    @FXML
    public void SearchStudent(ActionEvent event) throws IOException {
        try {
            System.out.println(getSelectedSeachIndex() + "" + SearchStudentField.getText());
            searchingDao.SearchEtudiant(getSelectedSeachIndex(), SearchStudentField.getText());
            FillUpUsersTable(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}