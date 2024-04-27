package master.iitn.model;

public class Matiere {
    private String nom_matiere;
    private int id_matiere;

    public Matiere(){

    }
    public Matiere(String nom_matiere){
        this.nom_matiere = nom_matiere;
    }

    public void setId(int id_matiere){
        this.id_matiere = id_matiere;
    }

    public int getId(){
        return id_matiere;
    }

    public String getNomMatiere(){
        return this.nom_matiere;
    }
    public void setNomMatiere(String nom_matiere){
        this.nom_matiere = nom_matiere;
    }
}
