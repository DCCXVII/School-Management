package master.iitn;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import master.iitn.dao.AdministateurDao;
import master.iitn.model.Roles;
import master.iitn.model.User;

public class AdminstrateurController {
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
    Roles role ;

    @FXML
    public void onBtnClick(ActionEvent event) throws IOException {
        
        try {
            int user_id =  0;
            String image = "";
            String n = nom.getText();
            String p = prenom.getText();
            String e = email.getText();
            String pass = password.getText();          
            String c = cin.getText();
            String tele = telephone.getText();

            //create user
            User user = new User(user_id, image, n, p, e, pass, Roles.Etudiant, c, tele);
            // insert user

            System.out.println(user.toString());
            administateurDao.AddUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}
