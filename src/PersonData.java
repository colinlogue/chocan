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

    //Constructor used for testing. Please don't remove
    public PersonData(String new_name, String new_street, String new_city, String new_state, String new_zip){
        name = new_name;
        address.street = new_street;
        address.city = new_city;
        address.state = new_state;
        address.ZIP = new_zip;
    }

    // private data
    private static int ident_len = 9;

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

    public void display_name_id() {
        System.out.printf(name);
        System.out.printf(" " + ident + "\n");
    }


    protected static String ident_to_string(int num) {
        return DataSource.ident_to_string(num, ident_len);
    }
}
