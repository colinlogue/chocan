import java.sql.SQLException;
import java.sql.*;

public class AddressData extends DataSource {

    public AddressData(){
        super();
    }

    public AddressData(String new_street, String new_city, String new_state, String new_ZIP){
        street = new_street;
        city = new_city;
        state = new_state;
        ZIP = new_ZIP;
    }

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
        String sql = "SELECT * FROM address WHERE AddressID = " + ident;
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(sql);) {
            AddressData add = new AddressData();

            //PreparedStatement stmt = conn.prepareStatement(sql);
            //replaces ? in string with add_id, creating a valid SQL statement


            //populate data members
            add.street = results.getString("street");
            add.city = results.getString("city");
            add.ZIP = results.getString("ZIP");
            add.state = results.getString("state");
            conn.close();
            return add;
        }
        catch (SQLException e) { throw e; }
    }

    // public methods
    public boolean write() throws SQLException {
        if (ident == 0)
        {
            String sql = "INSERT INTO address" +
                        "(street,city,state,ZIP,AddressID) " +
                        "values (?,?,?,?,?)";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                ident = get_next_ident();
                pstmt.setString(1,street);
                pstmt.setString(2, city);
                pstmt.setString(3, state);
                pstmt.setString(4, ZIP);
                pstmt.setInt(5, ident);
                pstmt.execute();
                conn.close();
            }
            catch (SQLException e) {
                throw e;
            }
            return true;
        }
        else {
            String sql = "UPDATE address SET" +
                    "(street,city,state,ZIP) " +
                    "= (?,?,?,?) WHERE AddressID = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, street);
                pstmt.setString(2, city);
                pstmt.setString(3, state);
                pstmt.setString(4, ZIP);
                pstmt.setInt(5, ident);
                // UPDATE
                pstmt.execute();
                conn.close();
            }
            catch (SQLException e)
            {
                throw e;
            }
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
            return results.getInt(1) + 1;
        }
    }

    public void display() {
        System.out.println(street + " " + city + ", " + state + " " + ZIP);
    }
}
