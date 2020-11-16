package eduspace.model;

import eduspace.vendor.DatabaseConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class departement {
    private int id;
    private int depId;
    private int chefId;
    private String nomDep;
    private String nomDomaine;

    public departement() {

    }

    public departement(int id) throws SQLException {
        this.id = id;
        Statement stmt = DatabaseConn.getConnect().createStatement();
        String sql = "SELECT * " +
                "FROM DEPARTEMENT "
                + "where DEP_ID =" + this.id + "";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            this.depId = rs.getInt("DEP_ID");
            this.chefId = rs.getInt("CHEF_ID");
            this.nomDep = rs.getString("NOM_DEP");
            this.nomDomaine = rs.getString("NOM_DOMAINE");
        }
    }


    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getNomDep() {
        return nomDep;
    }

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }


}
