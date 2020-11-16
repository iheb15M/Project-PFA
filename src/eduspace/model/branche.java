package eduspace.model;

import eduspace.vendor.DatabaseConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class branche {
    private int id;
    private String nom;
    private String type;
    private int depId;

    public branche() {

    }

    public branche(int id) throws SQLException {
        this.id = id;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM BRANCHE "
                + "where BRANCHE_ID =" + this.id;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            this.nom = rs.getString("SURNOM");
            this.type = rs.getString("TYPE");
            this.depId = rs.getInt("DEP_ID");
        } else {
            System.out.println("Branche n'existe pas ");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }
}
