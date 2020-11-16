package eduspace.model;

import eduspace.vendor.DatabaseConn;
import java.sql.*;


public class user {
    private String pseudo;
    private String password;
    private String type;
    private int idEmploye;
    private final Connection conn = DatabaseConn.getConnect();

    public user() {

    }

    public user(String pseudo) throws SQLException {
        this.pseudo = pseudo;
        String sql = "SELECT * FROM compte where pseudo LIKE '" + pseudo + "'";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {
            this.pseudo = rs.getString("PSEUDO");
            this.password = rs.getString("PASSWORD");
            this.type = rs.getString("TYPE");
            this.idEmploye = rs.getInt("ID_EMP");
        }
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
}
