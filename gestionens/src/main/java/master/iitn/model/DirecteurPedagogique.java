package master.iitn.model;

public class DirecteurPedagogique extends User{
    private int id_directeur;

    public DirecteurPedagogique(int user_id,String image, String nom, String prenom, String email, String password, Roles role, String cin, String phone ) {
        super(user_id, image, nom, prenom, email, password, role, cin, phone);
       

        this.id_directeur = user_id;
    }

    public int getId_directeur() {
        return id_directeur;
    }

    public void setId_directeur(int id_directeur) {
        this.id_directeur = id_directeur;
    }

    
}
