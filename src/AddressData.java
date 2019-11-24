import java.sql.SQLException;

public class AddressData extends DataSource {

    // public data
    public String street;
    public String city;
    public String ZIP;
    public String state;

    // db read/write
    public static AddressData retrieve(int ident) {
        return new AddressData();
    }

    // public methods
    public boolean write() throws SQLException {



        return true;
    }
}
