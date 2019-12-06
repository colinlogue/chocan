import java.sql.*;

public class ProviderData extends PersonData {

    public ProviderData(){
        super();
    }

    //Constructor used for testing. Please don't remove
    public ProviderData(String new_name, String new_street, String new_city, String new_state, String new_zip) {
        super(new_name, new_street, new_city, new_state, new_zip);
    }

    // these data members describe the names of the table and
    // columns in the database
    private static String table = "provider";
    private static String[] columns = {
        "ProviderID",
        "Name",
        "AddressID"
    };

    public boolean is_active;

    public static ProviderData retrieve(int ident) throws SQLException {
        //new
        String pro_id = ident_to_string(ident);
        String sql = "SELECT * FROM provider WHERE ProviderID = " + pro_id;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            ProviderData pro = new ProviderData();
            int address_id = results.getInt("AddressID");
            pro.address = AddressData.retrieve(address_id);
            pro.ident = Integer.parseInt(pro_id);
            pro.name = results.getString("Name");
            return pro;
        }
        catch (SQLException e) { throw e; }

    }

    public static void delete(int ident) throws SQLException {
        // drop row matching ident from provider table
        String pro_id = ident_to_string(ident);
        String sql = "DELETE FROM provider WHERE ProviderID = ?";

        try (Connection conn = connect(); //check this **
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, pro_id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*public void display() {
        System.out.println(name);
    }*/

    public static PersonData.status validate(int ident) throws SQLException {
        String sql = "select * from provider where ProviderID = " + ident_to_string(ident);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql)) {
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
        //if no id, insert new rows
        if (ident == 0){
            ident = get_next_ident();
            //write address first
            address.write();
            String sql = "INSERT INTO provider" +
                    "(ProviderID,Name,AddressID) " +
                    "values (?,?,?)";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setInt(1,ident);
                pstmt.setString(2, name);
                String add_id =  Integer.toString(address.ident);
                pstmt.setString(3, add_id);
                //String act  = Boolean.toString(is_active);
                //pstmt.setString(4,act);
                pstmt.execute();
            }
            catch (SQLException e) {
                throw e;
            }
            return true;
        }
        else {
            String sql = "UPDATE provider SET " +
                    "(Name,AddressID) " +
                    "= (?,?) WHERE ProviderID = ?";
            address.write();
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, name);
                String add_id =  Integer.toString(address.ident);
                pstmt.setString(2, add_id);
                //String act  = Boolean.toString(is_active);
                //pstmt.setString(3,act);
                pstmt.setInt(3,ident);
                pstmt.execute();
            }
            catch (SQLException e) {
                throw e;
            }
        }
        return true;
    }

    private int get_next_ident() throws SQLException {
        // finds the previous max ident string and increments by 1
        String sql = "select max(ProviderID) from provider";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql))
        {
            return Integer.parseInt(results.getString(1)) + 1;
        }
    }

    // test
    public static void main(String[] args) {
        try {
            ProviderData pro = ProviderData.retrieve(900006);
            pro.display();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void display_ids() {

    }
}
