import java.sql.SQLException;
import java.sql.*;

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
    public static AddressData retrieve(int ident) throws SQLException {
        //selects all columns from member row that matches id
        String sql = "SELECT * FROM address WHERE AddressID = ?";
        //establishes connection
        Connection conn = connect();
        //creates obj
        PreparedStatement stmt = conn.prepareStatement(sql);
        //replaces ? in string with add_id, creating a valid SQL statement
        stmt.setInt(1, ident);
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
        if (ident == 0)
        {
            ident = get_next_ident();
            String[] vals = new String[] {
              Integer.toString(ident),
              street,
              state,
              ZIP
        };
        insert(table, columns, vals);
      }
      return true;
    }

    private int get_next_ident() throws SQLException {
        // finds the previous max ident string and increments by 1
        String sql = "select max(AddressID) from address";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);)
        {
            return Integer.parseInt(results.getString(1)) + 1;
        }
    }

    public void display() {
        System.out.println(street);
    }

    public static void main(String[] args) {
        try {
            AddressData address = AddressData.retrieve(1);
            address.display();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
