// This is the base class for all classes that directly insert into
// and retrieve from the database.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static String url = "jdbc:sqlite:db/chocan.sqlite3";

    // all rows in all db tables can be identified by a unique int
    protected int ident;

    protected Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        DataSource ds = new DataSource();
        try {
            ds.connect();
            System.out.println("Connection established");
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    protected String ident_to_string(int num) {
        return "000000";
    }
}
