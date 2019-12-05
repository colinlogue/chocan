// This is the base class for accessing the data for people, i.e.
// members and providers.
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonData extends DataSource {
    public enum status {
        INVALID,        // if id is not in db
        VALID,          // if all is good
        SUSPENDED,      // if member hasn't paid dues
    }

    // public data
    public String name;
    public AddressData address;

    public PersonData(){
        name = null;
        address = new AddressData();
    }

    // private data
    private static int ident_len;

    // static methods
    public static PersonData.status validate(int ident, String table, String id_col) {
        String sql = "select * from " + table + " where " + id_col + " = " + ident_to_string(ident);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            if (results.first()) {
                if (results.getBoolean("is_active")) {
                    // ID exists and is active
                    return status.VALID;
                }
                else {
                    // ID exists and is inactive
                    return status.SUSPENDED;
                }
            }
            // ID does not exist
            else return status.INVALID;
        } catch (SQLException e) {
            return status.INVALID;
        }
    }

    // public methods
    public boolean write() throws SQLException {
        // writes data to the db
        // TODO
        return true;
    }

    public void display() {
        System.out.println(name);
        address.display();
    }


    protected static String ident_to_string(int num) {
        return DataSource.ident_to_string(num, ident_len);
    }
}
