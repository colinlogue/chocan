/*

Database access class for services.

 */

import java.sql.*;

public class ProviderDirectory extends DataSource {

    public int fee;
    public String label;

    private String fee_String() {
        String int_str = Integer.toString(ident);
        String start = int_str.substring(int_str.length() - 2);
        String end = int_str.substring(0, int_str.length() - 2);
        return "$" + start + "." + end;
    }

    public static ProviderDirectory retrieve(int ident) throws SQLException {
        //selects all columns from member row that matches id
        String sql = "SELECT * FROM session WHERE SessionID = " + Integer.toString(ident);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {

        }
        catch (SQLException e) { throw e; }
        return null;
    }
    private String ident_to_string(int num) {
        return DataSource.ident_to_string(num, 6);
    }
    public void display() {
        System.out.println("\nService " + Integer.toString(ident));
        System.out.println("Service code:   " + Integer.toString(ident));
        System.out.println("Label:          " + label);
        System.out.println("Fee:            " + "fee"); // TODO
        System.out.println();
    }

}
