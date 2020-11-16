package eduspace.model;

import eduspace.vendor.DatabaseConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class domaine {
    private int id;
    private String surnom;
    private char branche;

    public domaine(String nom_domaine) {

    }

    public domaine(int id) throws SQLException {
        this.id = id;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM DOMAINE "
                + "where DOMAINE_ID =" + this.id;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            this.surnom = rs.getString("SURNOM");
            this.branche = rs.getString("BRANCHE").charAt(0);
        } else {
            System.out.println("Domaine n'existe pas");
        }
        DatabaseConn.getConnect().close();
    }


    public int getId() {
        return id;
    }


    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public char getBranche() {
        return branche;
    }

    public void setBranche(char branche) {
        this.branche = branche;
    }
}
