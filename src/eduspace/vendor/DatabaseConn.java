package eduspace.vendor;
import java.io.IOException;

import java.sql.*;

public  class DatabaseConn {


    public static   Connection getConnect()  {
        String username = "eduspace";
        String password = "edu123";
        Connection con = null;
        try {
            //charger le pilote
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //creation de connection
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", username, password);

        } catch (Exception e) {
            System.out.println(e);
            try {
                AlertBox.display("error","database not connected");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return con;
    }


}
