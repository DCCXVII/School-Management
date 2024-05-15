package master.iitn.controller;

import java.io.File;
import java.io.FileInputStream;
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
import master.iitn.dao.ImprimerDao;
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



public class ImprimerController {
    ImprimerDao imprimerDao;
    
    
    @FXML
    private TextField TextFieldSearch_Student;
    @FXML
    private TableView<Etudiant> TableStudent;

    @FXML   
    private TableColumn<Matiere, Integer> CNE;
    @FXML
    private TableColumn<Matiere, String> Nom;
    @FXML
    private TableColumn<Matiere, String> Prenom;
    @FXML
    private TableColumn<Matiere, String> CIN;
    @FXML
    private TableColumn<Matiere, String> Classe;
    @FXML
    private TableColumn<Matiere, String> Email;


    // MatiereDao matiereService;
    // Utils utils;
    // String profilePath;

    public ImprimerController(){
        this.imprimerDao = new ImprimerDao();
        // this.imprimerDao = new MatiereDao();
        // this.utils = new Utils();
    }

    @FXML
    public void FillEtudiant() throws IOException {
        String cne = TextFieldSearch_Student.getText();

        CNE.setCellValueFactory(new PropertyValueFactory<>("Cne"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        CIN.setCellValueFactory(new PropertyValueFactory<>("Cin"));
        Classe.setCellValueFactory(new PropertyValueFactory<>("Classe"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));


        ObservableList<Etudiant> etudiants = imprimerDao.searchEtudiant(cne);
        TableStudent.setItems(etudiants);
    }

    @FXML
    public void Attestation() throws IOException{
        String cne = TextFieldSearch_Student.getText();

        imprimerDao.ImprimerAttestation(cne);
        
    }
}
