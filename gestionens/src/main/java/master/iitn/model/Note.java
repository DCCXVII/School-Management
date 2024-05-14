package master.iitn.model;

public class Note {
    private String annee;
    private String matiere;
    private String session1;
    private Double noteS1;
    private String resultatS1;
    private String session2;
    private Double noteS2;
    private String resultatS2;
    // private int ID_ETUDIANT;

    public Note() {
    }

    public Note(String annee, String matiere, Double noteS1, String resultatS1,
            Double noteS2, String resultatS2) {
        this.annee = annee;
        this.matiere = matiere;
        this.noteS1 = noteS1;
        this.resultatS1 = resultatS1;
        this.noteS2 = noteS2;
        this.resultatS2 = resultatS2;
    }

    public String getAnnee() {
        return annee;
    }

    public String getMatiere() {
        return matiere;
    }

    public String getSession1() {
        return session1;
    }

    public Double getNoteS1() {
        return noteS1;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setSession1(String session1) {
        this.session1 = session1;
    }

    public void setNoteS1(Double noteS1) {
        this.noteS1 = noteS1;
    }

    public void setResultatS1(String resultatS1) {
        this.resultatS1 = resultatS1;
    }

    public void setSession2(String session2) {
        this.session2 = session2;
    }

    public void setNoteS2(Double noteS2) {
        this.noteS2 = noteS2;
    }

    public void setResultatS2(String resultatS2) {
        this.resultatS2 = resultatS2;
    }

    public String getResultatS1() {
        return resultatS1;
    }

    public String getSession2() {
        return session2;
    }

    public Double getNoteS2() {
        return noteS2;
    }

    public String getResultatS2() {
        return resultatS2;
    }

    // public void setID_ETUDIANT(int ID_ETUDIANT) {
    //     this.ID_ETUDIANT = ID_ETUDIANT;
    // }

    // public int getID_ETUDIANT() {
    //     return ID_ETUDIANT;
    // }
}