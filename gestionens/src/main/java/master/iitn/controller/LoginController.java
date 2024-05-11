package master.iitn.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import master.iitn.App;
import master.iitn.dao.UserDao;
import master.iitn.model.Roles;
import master.iitn.model.User;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    UserDao userdao = new UserDao();

    @FXML
    private void loginUser(ActionEvent event) throws IOException {
        try {
            String EMAIL = email.getText();
            String PASSWORD = password.getText();
            User LoggedUser = userdao.LoginUser(EMAIL, PASSWORD);
            SessionController.getInstance().setUser(LoggedUser);
            Redirect(LoggedUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Redirect(User user) throws Exception {
        Roles userRole = user.getRole();

        switch (userRole) {
            case Etudiant:
                App.setRoot("view/EtudiantProfile", SessionController.getInstance().getUser());
                break;
            case Administrateur:
                App.setRoot("view/Admin", SessionController.getInstance().getUser());
                break;
            case Professeur:
                App.setRoot("view/Professeur", SessionController.getInstance().getUser());
                break;
            case DircteurPedagogique:
                App.setRoot("view/DirecteurPedagogique", SessionController.getInstance().getUser());
                break;
            default:
                // Handle unexpected cases or unassigned roles
                break;
        }

    }

}