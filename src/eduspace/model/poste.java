package eduspace.model;

import eduspace.vendor.DatabaseConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class poste {

    private int id;
    private float salaire;
    private String nom;

    public poste(int id) throws SQLException {
        this.id = id;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM POSTE "
                + "where ID_POST =" + this.id;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            this.setNom(rs.getString("TYPE_POST"));
            this.setSalaire(rs.getFloat("SALAIRE"));
        }
    }

    public poste() {

    }

    public int getId() {
        return id;
    }

    public float getSalaire() {
        return salaire;
    }

    public String getNom() {
        return nom;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
