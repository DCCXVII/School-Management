package master.iitn.model;

public class Etudiant extends User{
    private int cne;

    public Etudiant(int user_id,String image, String nom, String prenom, String email, String password, Roles role, String cin, String phone) {
        super(user_id, image, nom, prenom, email, password, role, cin, phone);
        this.cne = user_id;
    }

    public int getCne() {
        return cne;
    }   

    public void setCne(int cne) {
        this.cne = cne;
    }
    
}
