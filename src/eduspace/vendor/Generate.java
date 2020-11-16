package eduspace.vendor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Generate {

    public static String pseudo(String type) throws SQLException {
        Random rand = new Random();
        int int_random = rand.nextInt(8999) + 1000;
        String pseudo = type + int_random;
        while (!Generate.pseudoUnique(pseudo))
            pseudo = type + int_random;
        return pseudo;
    }

    public static boolean pseudoUnique(String pseudo) throws SQLException {
        String sql = "SELECT * " +
                "FROM COMPTE " +
                "WHERE PSEUDO LIKE '" + pseudo + "'";
        Statement stmt = DatabaseConn.getConnect().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return !rs.next();
    }
}
