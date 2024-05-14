package master.iitn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import master.iitn.dao.AdministrateurDao;
import master.iitn.dao.MatiereDao;
import master.iitn.model.Etudiant;
import master.iitn.model.Gender;
import master.iitn.model.Matiere;
import master.iitn.model.Roles;
import master.iitn.services.Utils;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class AdministrateurController implements Initializable {
    AdministrateurDao administrateurService;
    MatiereDao matiereService;
    Utils utils;
    String profilePath;

    public AdministrateurController(){
        this.administrateurService = new AdministrateurDao();
        this.matiereService = new MatiereDao();
        this.utils = new Utils();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the ToggleGroup for gender
        Genre = new ToggleGroup();
        RBtn_MaleStudent.setToggleGroup(Genre);
        RBtn_FemaleStudent.setToggleGroup(Genre);

        // Initialize the ChoiceBox
        // ComboBoxClasse.getItems().addAll("IITN", "GE");
        ComboBoxClasse.getItems().addAll(administrateurService.getClassNames()) ;
    }

    // Gender Section
    @FXML
    private ToggleGroup Genre;
    @FXML
    private RadioButton RBtn_MaleStudent;
    @FXML
    private RadioButton RBtn_FemaleStudent;

    // Class
    @FXML
    private ComboBox<String> ComboBoxClasse;

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
    // @FXML
    // private TextField PasswordEtudiant;
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
    @FXML 
    private ImageView ProfileStudent;




    @FXML
    private void addNewUser(ActionEvent event) {
        try {
            String nom = NomEtudiant.getText();
            String prenom = PrenomEtudiant.getText();
            String email = utils.generateEmail(nom, prenom);
            String password = nom;
            String telephone = PhoneEtudiant.getText();
            String cin = CinEtudiant.getText();
            String cne = CneEtudiant.getText();
            String level = LevelEtudiant.getText();
            String selectedClass = ComboBoxClasse.getValue();
            LocalDate selectedDate = DateNaissanceEtudiant.getValue();
            Date dateOfBirth = (selectedDate != null) ? java.sql.Date.valueOf(selectedDate) : null;
            
            // System.out.println(profilePath.toString());
        
            // String image = "default";
            Roles role = Roles.Etudiant;
            Gender gender = getSelectedGender().equals("Homme") ? Gender.Homme : Gender.Femme;
            String anneeUniversitaier = "2023-2024";

            Etudiant etudiant = new Etudiant(0, profilePath, nom, prenom, email, password, role, cin, cne, telephone, gender,
                    dateOfBirth, level, selectedClass, anneeUniversitaier);

                    System.err.println();
            
            administrateurService.addEtudiant(etudiant);
        } catch (Exception e) {
            logError("Error adding new student: ", e);
        }
    }

    @FXML
    private void addNewMatiere(ActionEvent event) {
        try {
            String nomMatiere = TextField_AddMatiere.getText();
            Matiere matiere = new Matiere(nomMatiere);
            matiereService.addMatiere(matiere);
            FillMatieres(event);
        } catch (Exception e) {
            logError("Error adding new matiere: ", e);
        }
    }

    @FXML
    public void FillMatieres(ActionEvent event) {
        // Bind the table columns to the properties of the Matiere class
        IdMatiere.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomMatiere.setCellValueFactory(new PropertyValueFactory<>("nomMatiere"));

        // Fetch all Matiere objects from the database
        ObservableList<Matiere> matieres = matiereService.getAllMatieres();
        TableMatiere.setItems(matieres);
    }

    @FXML
    private void ImportStudentImage() throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(new Stage());

        try {
            this.profilePath = file.getAbsolutePath();

            FileInputStream inputStream = new FileInputStream(profilePath);
            ProfileStudent.setImage(new Image(inputStream)); 

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private String getSelectedGender() {
        if (RBtn_MaleStudent.isSelected()) {
            return "Homme";
        } else if (RBtn_FemaleStudent.isSelected()) {
            return "Femme";/*  */
        }
        return null;
    }

    private void logError(String message, Exception e) {
        // Implement logging mechanism
        System.err.println(message + e.getMessage());
    }

}
