package master.iitn;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
            LoggedUser.toString();

            System.out.println("enterd succefully!!");
            Redirect(LoggedUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Redirect(User user) throws IOException {
        try {
            if (user.getRole() == Roles.Etudiant) {
                App.setRoot("view/primary");
            } else {
                if (user.getRole() == Roles.Administrateur) {
                    App.setRoot("view/Admin");
                } else {
                    if(user.getRole()==Roles.Professeur){
                        App.setRoot("view/Professeur");
                    }else{
                        if(user.getRole()==Roles.DircteurPedagogique)
                        {
                            App.setRoot("view/DirecteurPedagogique");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
