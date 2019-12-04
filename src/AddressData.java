import java.sql.SQLException;

public class AddressData extends DataSource {

    // public data
    public String street;
    public String city;
    public String ZIP;
    public String state;

    private static String table = "address";
    private static String[] columns = {
        "street",
        "city",
        "state",
        "ZIP"
    };

    // db read/write
    public static AddressData retrieve(int ident) {
        //convert int to string
        String add_id = ident_to_string(ident);
        //selects all columns from member row that matches id
        String sql = "SELECT * FROM address WHERE ident = ?";
        //establishes connection
        Connection conn = connect();
        //creates obj
        PreparedStatement stmt = conn.prepareStatement(sql);
        //replaces ? in string with add_id, creating a valid SQL statement
        stmt.setString(1, add_id);
        //queries appropriate table for statement
        ResultSet results = stmt.executeQuery();
        AddressData add = new AddressData();
        //populate data members
        add.street = results.getString("street");
        add.city = results.getString("city");
        add.ZIP = results.getString("ZIP");
        add.state = results.getString("state");
        conn.close();
        return add;
    }

    // public methods
    public boolean write() throws SQLException {
        //ident = 2;
        if (ident == 0)
        {
        String[] vals = new String[] {
            ident_to_string(ident),
            street,
            state,
            ZIP
        };
        
      }


        return true;
    }

//get_next_ident()????

    public void display() {

    }
}
