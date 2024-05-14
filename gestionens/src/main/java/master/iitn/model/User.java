package master.iitn.model;

import java.util.Date;


public class User {
    private int ID_USER;
    private String cin;
    private String image;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Roles role;
    private Date date_naissance;
    private Gender geneder;
    private String phone;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int ID_USER, String image, String nom, String prenom, String email, String password, Roles role,
            String cin, String phone, Gender gender, Date date2naissance) {
        this.ID_USER = ID_USER;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cin = cin;
        this.phone = phone;
        this.date_naissance = date2naissance;
        this.geneder = gender;
    }

    public User getUser() {
        return this;
    }

    @Override
    public String toString() {
        return "User [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", role="
                + role
                + ", cin=" + cin + ", phone=" + phone + ", ID_USER=" + ID_USER + "date de naiss" + date_naissance
                + "gender : " + geneder + "]";
    }

    public int getId() {
        return ID_USER;
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public Gender getGender() {
        return geneder;
    }

    public void setId(int ID_USER) {
        this.ID_USER = ID_USER;
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

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setGender(Gender gender) {
        this.geneder = gender;
    }

    public boolean logout(User user) {
        return true;
    }

}
