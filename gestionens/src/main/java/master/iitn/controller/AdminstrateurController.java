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
import master.iitn.dao.AdministateurDao;
import master.iitn.dao.MatiereDao;
import master.iitn.model.Etudiant;
import master.iitn.model.Gender;
import master.iitn.model.Matiere;
import master.iitn.model.Roles;

public class AdminstrateurController implements Initializable {
    @FXML
    private ToggleGroup Genre;

    @FXML
    private RadioButton RBtn_MaleStudent;

    @FXML
    private RadioButton RBtn_FemaleStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RBtn_MaleStudent.setToggleGroup(Genre);
        RBtn_FemaleStudent.setToggleGroup(Genre);
    }

    public String getSelectedGender() {
        if (RBtn_MaleStudent.isSelected()) {
            return "Homme";
        } else if (RBtn_FemaleStudent.isSelected()) {
            return "Femme";
        }
        return null;
    }

    // Fileds : AddMatiere
    @FXML
    private TextField TextField_AddMatiere;

    @FXML
    private TableView<Matiere> TableMatiere;

    @FXML
    private TableColumn<Matiere, Integer> IdMatiere;
    @FXML
    private TableColumn<Matiere, String> NomMatiere;

    // Fields : Add student;
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
    private DatePicker DateNaissanceEtudiant;

    @FXML
    private Button AddEtudiant;

    @FXML
    private Button Cancel;

    MatiereDao matiereDao = new MatiereDao();

    Utils utils = new Utils();

    AdministateurDao administateurDao = new AdministateurDao();

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

            // Check if a date has been selected in the DatePicker
            LocalDate selectedDate = DateNaissanceEtudiant.getValue();
            Date dob = (selectedDate != null) ? java.sql.Date.valueOf(selectedDate) : null;

            String image = "default";
            Roles role = Roles.Etudiant;
            Gender gender = getSelectedGender().equals("Homme") ? Gender.Homme : Gender.Femme;

            Etudiant etudiant = new Etudiant(0, image, nom, prenom, email, pass, role, cin, cne, tele, gender, dob,
                    level);
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
}