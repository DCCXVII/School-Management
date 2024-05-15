package master.iitn.model;

public class Classe {
    public int ID_CLASS;
    public String NOM_CLASS;

    public Classe() {
    }

    public Classe(int ID_CLASS, String NOM_CLASS) {
        this.ID_CLASS = ID_CLASS;
        this.NOM_CLASS = NOM_CLASS;
    }

    public int getID_CLASS() {
        return ID_CLASS;
    }

    public void setID_CLASS(int ID_CLASS) {
        this.ID_CLASS = ID_CLASS;
    }

    public String getNOM_CLASS() {
        return NOM_CLASS;
    }

    public void setNOM_CLASSE(String NOM_CLASS) {
        this.NOM_CLASS = NOM_CLASS;
    }
}
