package master.iitn.model;



public class User {
    private int user_id;
    private String cin;
    private String image;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    private Roles role;

    public User(){
        
    }
    public User(int user_id, String nom, String prenom, String email, String password, Roles role, String cin) {
        this.user_id = user_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.cin = cin;
        this.role = role;
        
    }

    public User(int user_id,String image, String nom, String prenom, String email, String password, Roles role) {
        // super(user_id, nom, prenom, email, password, role);
        this.image = image;
    }

    public User getUser(){
        return this;
    }

    @Override
    public String toString() {
        return "User [cin=" + cin + ", email=" + email + ", image=" + image + ", nom=" + nom + ", password=" + password
                + ", prenom=" + prenom + ", role=" + role + ", user_id=" + user_id + "]";
    }

    public int getId() {
        return user_id;
    }

    public String getCin() {
        return cin;
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

    public Roles getRole() {
        return role;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public void setRole(Roles role) {
        this.role = role;
    }

    
    public boolean login(User user){
        return true;
    }

    public boolean logout(User user){
        return true;
    }

    
}
