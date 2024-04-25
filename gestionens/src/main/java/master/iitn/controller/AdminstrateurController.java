package master.iitn.controller;

import java.io.IOException;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import master.iitn.dao.AdministateurDao;
import master.iitn.model.Gender;
import master.iitn.model.Roles;
import master.iitn.model.User;

public class AdminstrateurController {
    // private static final Enum FEMALE = null;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField telephone;

    @FXML
    private TextField cin;

    @FXML
    private Button ajouter;

    AdministateurDao administateurDao = new AdministateurDao();
    Roles role;
    Date dob = new Date(2000 - 01 - 01);

    @FXML
    private void addNewUser(ActionEvent event) throws IOException {

        try {
            int user_id = 0;
            String image = "";
            String n = nom.getText();
            String p = prenom.getText();
            String e = email.getText();
            String pass = password.getText();
            String c = cin.getText();
            String tele = telephone.getText();
            Gender gender = Gender.Homme;

            // create user
            User user = new User(user_id, image, n, p, e, pass, Roles.Etudiant, c, tele, gender, dob);
            // insert user

            System.out.println(user.toString());
            administateurDao.AddUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
