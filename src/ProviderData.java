import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProviderData extends PersonData {

    public static ProviderData retrieve(int ident) {
        return new ProviderData();
    }

    public static void delete(int ident) throws SQLException {
        // drop row matching ident from provider table
    }

    public void display() {
    }

    public static status validate(int ident) {
        String sql = "select * from provider where ProviderID = " + ident_to_string(ident);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            if (results.first()) {
                return status.VALID;
            }
            // ID does not exist
            else return status.INVALID;
        } catch (SQLException e) {
            return status.INVALID;
        }
    }

    public boolean write() throws SQLException {
        return true;
    }

}
