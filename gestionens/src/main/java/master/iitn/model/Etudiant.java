package master.iitn.model;

import java.util.Date;

public class Etudiant extends User {
    private String cne;
    private String niveau;
    private String ANNEE_UNIVERSITAIRE;

    private String Class;

    public Etudiant() {
        super();
    }

    public Etudiant(int ID_USER, String image, String nom, String prenom, String email, String password, Roles role,
            String cin, String cne, String phone, Gender gender, Date date2naissance, String niveau, String Class,
            String anneeUniversitaire) {
        super(ID_USER, image, nom, prenom, email, password, role, cin, phone, gender, date2naissance);
        this.cne = cne;
        this.niveau = niveau;
        this.Class = Class;
        this.ANNEE_UNIVERSITAIRE = anneeUniversitaire;
    }

    // costumized constructors:

    // to retrieve more info about an etudiant from the database
    public Etudiant(String cne, String Classe, String niveau, String anneeUniversitaire) {
        this.cne = cne;
        this.Class = Classe;
        this.niveau = niveau;
        this.ANNEE_UNIVERSITAIRE = anneeUniversitaire;
    }

    // to use in the Etudiant space:

    public Etudiant(User user,Etudiant etudiant) {
        super(user.getId(), user.getImage(), user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword(),
                user.getRole(), user.getCin(), user.getPhone(), user.getGender(), user.getDate_naissance());
        this.cne = etudiant.getCne();
        this.niveau = etudiant.getLevel();
        this.Class = etudiant.getClasse();
        this.ANNEE_UNIVERSITAIRE = etudiant.getAnneeUniversitaire();
    }

    public String getLevel() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;

    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.ANNEE_UNIVERSITAIRE = anneeUniversitaire;
    }

    public String getAnneeUniversitaire() {
        return this.ANNEE_UNIVERSITAIRE;
    }

    @Override
    public String toString() {
        return super.toString() + "Etudiant [cne=" + cne + ", level=" + niveau + "]";
    }

    public String getClasse() {
        return this.Class;
    }

    public void setClasse(String Class) {
        this.Class = Class;
    }

}
