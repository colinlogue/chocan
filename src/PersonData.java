// This is the base class for accessing the data for people, i.e.
// members and providers.

import java.sql.SQLException;

public class PersonData extends DataSource {
    public enum status {
        INVALID,        // if id is not in db
        VALID,          // if all is good
        SUSPENDED,      // if member hasn't paid dues
    }

    // public data
    public String name;
    public AddressData address;

    // private data
    private static int ident_len;

    // static methods
    public static status validate(int ident) {
        // TODO
        return status.VALID;
    }

    // public methods
    public boolean write() throws SQLException {
        // writes data to the db
        // TODO
        return true;
    }

    public void display() {
        // displays data to the console
        // TODO
    }

    protected static String ident_to_string(int num) {
        return DataSource.ident_to_string(num, ident_len);
    }
}
