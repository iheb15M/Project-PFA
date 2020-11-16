package eduspace.model;

import eduspace.vendor.DatabaseConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class employe {

    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String adresse;
    private String dateNaiss;
    private int poste_Id;
    private int tel;
    private int id;
    private int zip;
    private String Gender;

    public employe() {
    }

    public employe(int id) throws SQLException {
        this.id = id;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM EMPLOYE "
                + "where ID_EMP =" + this.id;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            this.setNom(rs.getString("NOM"));
            this.setPrenom(rs.getString("PRENOM"));
            this.setPoste_Id(rs.getInt("ID_POSTE"));
            this.setCin(rs.getString("CIN"));
            this.setTel(rs.getInt("NUM_TEL"));
            this.setEmail(rs.getString("MAIL"));
            this.setAdresse(rs.getString("ADRESSE"));
            this.setGender(rs.getString("GENDER"));
            this.setZip(rs.getInt("ZIP"));

        } else {
            System.out.println("Employe n'existe pas");
        }
        DatabaseConn.getConnect().close();
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }


    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPoste_Id() {
        return poste_Id;
    }

    public void setPoste_Id(int poste_Id) {
        this.poste_Id = poste_Id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
}
