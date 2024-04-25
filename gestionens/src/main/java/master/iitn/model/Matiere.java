package master.iitn.model;

public class Matiere {
    private String nom_matiere;

    public Matiere(){

    }
    public Matiere(String nom_matiere){
        this.nom_matiere = nom_matiere;
    }

    // @Override
    public String getNomMatiere(){
        return this.nom_matiere;
    }
    public void setNomMatiere(){
        this.nom_matiere = nom_matiere;
    }
}
