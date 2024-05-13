package master.iitn.model;

public class Note {
    int ID_NOTE;
    int ID_ETUDIANT;
    String NOM_MATIERE;
    float noteSemestre;
    int Annnee;
    Mentions mention;
    String Semestre;

    public Note() {

    }

    public Note(int ID_NOTE, int ID_ETUDIANT, float noteSemestre, int Annnee, Mentions mention, String Semestre) {
        this.ID_NOTE = ID_NOTE;
        this.ID_ETUDIANT = ID_ETUDIANT;
        this.noteSemestre = noteSemestre;
        this.Annnee = Annnee;
        this.mention = mention;
        this.Semestre = Semestre;
    }

    public int getID_NOTE() {
        return ID_NOTE;
    }

    public void setID_NOTE(int ID_NOTE) {
        this.ID_NOTE = ID_NOTE;
    }

    public void setID_MATIERE(String NOM_MATIERE) {
        this.NOM_MATIERE = NOM_MATIERE;
    }

    public String getID_MATIERE() {
        return this.NOM_MATIERE;
    }

    public int getID_ETUDIANT() {
        return ID_ETUDIANT;
    }

    public void setID_ETUDIANT(int ID_ETUDIANT) {
        this.ID_ETUDIANT = ID_ETUDIANT;
    }

    public float getNoteSemestre() {
        return noteSemestre;
    }

    public void setNoteSemestre(float noteSemestre) {
        this.noteSemestre = noteSemestre;
    }

    public int getAnnnee() {
        return Annnee;
    }

    public void setAnnnee(int Annnee) {
        this.Annnee = Annnee;
    }

    public Mentions getMention() {
        return mention;
    }

    public void setMention(Mentions mention) {
        this.mention = mention;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }

    public Note getNote() {
        return this;
    }

    @Override
    public String toString() {
        return "Note{" + "ID_NOTE=" + ID_NOTE + ", ID_ETUDIANT=" + ID_ETUDIANT + ", noteSemestre=" + noteSemestre
                + ", Annnee=" + Annnee + ", mention=" + mention + ", Semestre=" + Semestre + '}';
    }

}
