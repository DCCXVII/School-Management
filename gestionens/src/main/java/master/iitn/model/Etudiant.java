package master.iitn.model;
import java.util.Date;



public class Etudiant extends User {
    private int ID_ETUDIANT;
    private String cne;
    private String niveau;
    private String annee_universitaire;
    private String classe;

    public Etudiant() {
        super();
    }

    public Etudiant(int ID_USER, String image, String nom, String prenom, String email, String password, Roles role,
            String cin, String cne, String phone, Gender gender, Date date2naissance, String niveau, String classe,
            String anneeUniversitaire) {
        super(ID_USER, image, nom, prenom, email, password, role, cin, phone, gender, date2naissance);
        this.cne = cne;
        this.niveau = niveau;
        this.classe = classe;
        this.annee_universitaire = anneeUniversitaire;
    }

    // costumized constructors:

    // to retrieve more info about an etudiant from the database
    public Etudiant(String cne, String classe, String niveau, String anneeUniversitaire) {
        this.cne = cne;
        this.classe = classe;
        this.niveau = niveau;
        this.annee_universitaire = anneeUniversitaire;
    }

    // to use in the Etudiant space:

    public Etudiant(User user,Etudiant etudiant) {
        super(user.getId(), user.getImage(), user.getNom(), user.getPrenom(), user.getEmail(), user.getPassword(),
                user.getRole(), user.getCin(), user.getPhone(), user.getGender(), user.getDate_naissance());
        this.cne = etudiant.getCne();
        this.niveau = etudiant.getLevel();
        this.classe = etudiant.getClasse();
        this.annee_universitaire = etudiant.getAnneeUniversitaire();
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
        this.annee_universitaire = anneeUniversitaire;
    }

    public String getAnneeUniversitaire() {
        return this.annee_universitaire;
    }

    @Override
    public String toString() {
        return super.toString() + "Etudiant [cne=" + cne + ", level=" + niveau + "]";
    }

    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int setID_ETUDIANT(int ID_ETUDIANT) {
        return this.ID_ETUDIANT = ID_ETUDIANT;
    }

    public int getID_ETUDIANT() {
        return this.ID_ETUDIANT;
    }

}
