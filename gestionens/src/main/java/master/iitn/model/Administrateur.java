package master.iitn.model;

import java.sql.Date;

public class Administrateur extends User{
    private int admin_id;

    public Administrateur(int user_id, String image, String nom, String prenom, String email, String password, Roles role, String cin,String phone,Gender gender,Date date2naissance) {
        super(user_id, image, nom, prenom, email, password, role, cin, phone, gender,date2naissance);
        this.admin_id = user_id;
    }

    public int getadmin_id() {
        return admin_id;
    }

    public void setadmin_id(int admin_id) {
        this.admin_id = admin_id;
    }


    
}
