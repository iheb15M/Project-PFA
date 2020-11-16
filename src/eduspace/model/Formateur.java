package eduspace.model;

import eduspace.vendor.DatabaseConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Formateur extends employe {
    private String specialite;


    public Formateur() {

    }

    public Formateur(int id) throws SQLException {
        super(id);
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT SPECIALITE FROM FORMATEUR " +
                "WHERE ID_EMP =" + id;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            this.setSpecialite(rs.getString("SPECIALITE"));
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
