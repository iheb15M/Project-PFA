package eduspace.model;
import eduspace.vendor.DatabaseConn;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class salle {
    private int id;
    private int capacite;
    private String surnom;
    private String type;
    private int depId;

    public salle() {

    }


    public salle(int id) throws SQLException {
        this.id = id;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM SALLE "
                + "where ID_SALLE =" + this.id + "";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            this.capacite = rs.getInt ("CAPACITE");
            this.surnom = rs.getString ("SURNOM");
            this.type = rs.getString ("TYPE");
            this.depId = rs.getInt ("DEP_ID");

        } else {
            System.out.println("Salle n'existe pas");
        }
        DatabaseConn.getConnect().close();
    }

    public int getId() {
        return id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
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

    public void setCapacite(Callback cellValueFactory) {
    }
}
