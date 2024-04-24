package master.iitn.model;

import java.sql.Date;
import java.util.List;

public class Professeur extends User{
    public List<Matiere> matieres;
    public List<Module> Modules;
    public List<Classe> classes;
    private int prof_id;

    public Professeur(int user_id,String image, String nom, String prenom, String email, String password, Roles role, String cin, String phone,Gender gender, Date date2naissance) {
        super(user_id, image, nom, prenom, email, password, role, cin, phone, gender, date2naissance);
        this.prof_id = user_id;
    }
}
