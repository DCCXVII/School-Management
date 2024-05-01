package master.iitn.model;

import java.util.Date;

public class Etudiant extends User {
    private String cne;
    private String level;

    private String Class;

    public Etudiant() {
        super();
    }

    public Etudiant(int user_id, String image, String nom, String prenom, String email, String password, Roles role,
            String cin, String cne, String phone, Gender gender, Date date2naissance, String level, String Class) {
        super(user_id, image, nom, prenom, email, password, role, cin, phone, gender, date2naissance);
        this.cne = cne;
        this.level = level;
        this.Class = Class;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;

    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    @Override
    public String toString() {
        return super.toString() + "Etudiant [cne=" + cne + ", level=" + level + "]";
    }

    public  String getClasse() {
        return this.Class;
    }

    public void setClasse(String Class) {
        this.Class = Class;
    }

}
