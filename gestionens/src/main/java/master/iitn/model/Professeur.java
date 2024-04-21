package master.iitn.model;

import java.util.List;

public class Professeur extends User{
    public List<Matiere> matieres;
    public List<Module> Modules;
    public List<Classe> classes;
    private int prof_id;

    public Professeur(int user_id,String image, String nom, String prenom, String email, String password, Roles role) {
        super(user_id, image, nom, prenom, email, password, role);
        this.prof_id = user_id;
    }
}
