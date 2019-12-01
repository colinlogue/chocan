// This is the base class for all classes that directly insert into
// and retrieve from the database.

import java.sql.*;

public abstract class DataSource {
    private static String url = "jdbc:sqlite:db/chocan.sqlite3";

    // all rows in all db tables can be identified by a unique int
    protected int ident;

    protected static Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    protected static String ident_to_string(int num, int ident_len) {
        StringBuilder id_str = new StringBuilder(Integer.toString(num));
        while (id_str.length() < ident_len) {
            id_str.insert(0, '0');
        }
        return id_str.toString();
    }

    protected static void insert (String table, String[] cols, String[] vals)
            throws SQLException
    {
        // create insert statement
        String sql = "INSERT INTO table(cols) VALUES(vals)";
        // replace table with table name
        sql = sql.replaceAll("table", table);
        // build col and val strings from arrays
        int num_cols = cols.length;
        StringBuilder colstr = new StringBuilder();
        StringBuilder valstr = new StringBuilder();
        for (int i = 0; i < num_cols; ++i) {
            colstr.append(cols[i]);
            colstr.append(',');
            valstr.append('\'');
            valstr.append(vals[i]);
            valstr.append('\'');
            valstr.append(',');
        }
        // remove final commas
        int last_comma = colstr.lastIndexOf(",");
        if (last_comma > 0) colstr.deleteCharAt(last_comma);
        last_comma = valstr.lastIndexOf(",");
        if (last_comma > 0) valstr.deleteCharAt(last_comma);
        // replace cols and vals in sql string
        sql = sql.replaceAll("cols", colstr.toString());
        sql = sql.replaceAll("vals", valstr.toString());
        // execute sql statement
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            // rethrow if error
            throw e;
        }
    }

    // abstract public methods
    public abstract void display();

    // utility functions
    public static String pad_to(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < n) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
