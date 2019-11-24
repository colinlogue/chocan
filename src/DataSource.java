// This is the base class for all classes that directly insert into
// and retrieve from the database.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataSource {
    private static String url = "jdbc:sqlite:db/chocan.sqlite3";

    // all rows in all db tables can be identified by a unique int
    protected int ident;

    protected Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    protected static String ident_to_string(int num, int ident_len) {
        StringBuilder id_str = new StringBuilder(Integer.toString(num));
        while (id_str.length() < ident_len) {
            id_str.insert(0, '0');
        }
        return id_str.toString();
    }

    // abstract public methods
    public abstract void display();
}
