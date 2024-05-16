package master.iitn.controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import master.iitn.dao.ImprimerDao;
import master.iitn.model.Etudiant;
import master.iitn.model.Matiere;

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

    public ImprimerController() {
        this.imprimerDao = new ImprimerDao();
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
    public void Attestation() throws IOException {
        String cne = TextFieldSearch_Student.getText();

        imprimerDao.ImprimerAttestation(cne);

    }
}
