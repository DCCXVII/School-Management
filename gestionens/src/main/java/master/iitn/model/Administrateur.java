package master.iitn.model;

public class Administrateur extends User{
    private int admin_id;

    public Administrateur(int user_id,String image, String nom, String prenom, String email, String password, Roles role) {
        super(user_id, image, nom, prenom, email, password, role);
        this.admin_id = user_id;
    }

    public int getadmin_id() {
        return admin_id;
    }

    public void setadmin_id(int admin_id) {
        this.admin_id = admin_id;
    }


    
}
