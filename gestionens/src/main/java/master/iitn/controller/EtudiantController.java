package master.iitn.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import master.iitn.dao.EtudiantDao;
import master.iitn.model.Etudiant;
import master.iitn.model.User;

public class EtudiantController implements Initializable {

    // going to use this to get the etudiant info from the database
    private EtudiantDao etudiantDao = new EtudiantDao();

    // ------ Etudiant Data -------
    @FXML
    private Label Nom;

    @FXML
    private Label Prenom;

    @FXML
    private Label Cne;

    @FXML
    private Label Cin;

    @FXML
    private Label date2naissance;

    @FXML
    private Label Email;

    @FXML
    private Label Phone;

    @FXML
    private Label AnneeUniv;

    @FXML
    private Label Classe;

    @FXML
    private Label Level;

    @FXML
    private Label SessionEtudiant;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User PrimiteUser = SessionController.getInstance().getUser();

        Etudiant currentEtudiant = new Etudiant(PrimiteUser, etudiantDao.getEtudiantById(PrimiteUser.getId()));

        if (currentEtudiant != null) {
            setFields(currentEtudiant);
            System.out.println("user from etudiant controller : " + currentEtudiant.toString());
        }
    }

    private void setFields(Etudiant etudiant) {
        Nom.setText(etudiant.getNom());
        Prenom.setText(etudiant.getPrenom());
        Cne.setText(etudiant.getCne());
        Cin.setText(etudiant.getCin());
        Email.setText(etudiant.getEmail());
        date2naissance.setText(etudiant.getDate_naissance().toString());
        Phone.setText(etudiant.getPhone());
        AnneeUniv.setText(etudiant.getAnneeUniversitaire());
        Classe.setText(etudiant.getClasse());
        Level.setText(etudiant.getLevel());
        SessionEtudiant.setText("Bonjour, " + etudiant.getNom() + " " + etudiant.getPrenom());
    }

}
