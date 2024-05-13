package master.iitn.model;

import java.io.FileInputStream;
import java.sql.Date;

import javafx.scene.image.Image;

public class Administrateur extends User {
    private int admin_id;

    public Administrateur() {
    }

    public Administrateur(int ID_USER, String image, String nom, String prenom, String email, String password,
            Roles role, String cin, String phone, Gender gender, Date date2naissance) {
        super(ID_USER, image, nom, prenom, email, password, role, cin, phone, gender, date2naissance);
        this.admin_id = ID_USER;
    }

    public int getadmin_id() {
        return admin_id;
    }

    public void setadmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

}
