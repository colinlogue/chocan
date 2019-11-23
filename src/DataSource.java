// This is the base class for all classes that directly insert into
// and retrieve from the database.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private Connection conn;
    private static String url = "jdbc:sqlite:db/chocan.sqlite3";

    // all rows in all db tables can be identified by a unique int
    protected int ident;

    private void connect() throws SQLException {
        conn = DriverManager.getConnection(url);
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
}
