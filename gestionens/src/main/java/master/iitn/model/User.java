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
    private String phone;

    public User(){
        
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(int user_id, String image, String nom, String prenom, String email, String password, Roles role, String cin,String phone) {
        this.user_id = user_id;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cin = cin;
        this.phone = phone;
    }

    public User getUser(){
        return this;
    }

    @Override
    public String toString() {
        return "User [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", role=" + role
                + ", cin=" + cin + ", phone=" + phone + ", user_id=" + user_id + "]";
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

    public String getPhone() {
        return phone;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
   

    public boolean logout(User user){
        return true;
    }

    
}
