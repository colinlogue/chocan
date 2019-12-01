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

    public static PersonData.status validate(int ident) throws SQLException {
        String sql = "select * from provider where ProviderID = " + ident_to_string(ident);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            if (results.next()) {
                return status.VALID;
            }
            // ID does not exist
            else return status.INVALID;
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean write() throws SQLException {
        return true;
    }

    // test
    public static void main(String[] args) {
        try {
            PersonData.status valid = ProviderData.validate(900099);
            String status = "";
            if (valid == PersonData.status.VALID) {
                status += "valid";
            } else if (valid == PersonData.status.INVALID) {
                status += "invalid";
            } else if (valid == PersonData.status.SUSPENDED) {
                status += "suspended";
            } else {
                status += "whoopsie";
            }
            System.out.println(status);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
