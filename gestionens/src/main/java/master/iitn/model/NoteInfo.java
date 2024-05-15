package master.iitn.model;

public class NoteInfo {
    private String ID_ETUDIANT;
    private String nomkaml;
    private String NOM_CLASS;
    private String CNE;
    private String NOTE_NORMALE;
    private String NOTE_RATTRAPAGE;

    public NoteInfo(String ID_ETUDIANT, String nomkaml, String NOM_CLASS, String CNE, String NOTE_NORMALE,
            String NOTE_RATTRAPAGE) {
        this.ID_ETUDIANT = ID_ETUDIANT;
        this.nomkaml = nomkaml;
        this.NOM_CLASS = NOM_CLASS;
        this.CNE = CNE;
        this.NOTE_NORMALE = NOTE_NORMALE;
        this.NOTE_RATTRAPAGE = NOTE_RATTRAPAGE;
    }

    public String getID_ETUDIANT() {
        return ID_ETUDIANT;
    }

    public void setID_ETUDIANT(String ID_ETUDIANT) {
        this.ID_ETUDIANT = ID_ETUDIANT;
    }

    public String getNomkaml() {
        return nomkaml;
    }

    public void setNomkaml(String nomkaml) {
        this.nomkaml = nomkaml;
    }

    public String getNOM_CLASS() {
        return NOM_CLASS;
    }

    public void setNOM_CLASS(String NOM_CLASS) {
        this.NOM_CLASS = NOM_CLASS;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    public String getNOTE_NORMALE() {
        return NOTE_NORMALE;
    }

    public void setNOTE_NORMALE(String NOTE_NORMALE) {
        this.NOTE_NORMALE = NOTE_NORMALE;
    }

    public String getNOTE_RATTRAPAGE() {
        return NOTE_RATTRAPAGE;
    }

    public void setNOTE_RATTRAPAGE(String NOTE_RATTRAPAGE) {
        this.NOTE_RATTRAPAGE = NOTE_RATTRAPAGE;
    }

    @Override
    public String toString() {
        return "NoteInfo [CNE=" + CNE + ", ID_ETUDIANT=" + ID_ETUDIANT + ", NOM_CLASS=" + NOM_CLASS + ", NOTE_NORMALE="
                + NOTE_NORMALE + ", NOTE_RATTRAPAGE=" + NOTE_RATTRAPAGE + ", nomkaml=" + nomkaml + "]";
    }
}