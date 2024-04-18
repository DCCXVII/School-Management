package master.iitn.model;



public class User {
    private int user_id;
    private String image;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;
    
    public User(int user_id, String nom, String prenom, String email, String password, String role) {
        this.user_id = user_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int user_id,String image, String nom, String prenom, String email, String password, String role) {
        // super(user_id, nom, prenom, email, password, role);
        this.image = image;
    }

    public User getUser(){
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }   

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    




}
