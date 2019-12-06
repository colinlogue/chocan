import java.sql.*;
/*
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/
public class ProviderData extends PersonData {

    // these data members describe the names of the table and
    // columns in the database
    private static String table = "provider";
    private static String[] columns = {
        "ProviderID",
        "Name",
        "AddressID"
    };

    // all other data members are derived from PersonData
    public boolean is_active;

    public static ProviderData retrieve(int ident) throws SQLException {
        //convert int to String
        String pro_id = ident_to_string(ident);  //note: this differs from AddressData because handled in PersonData
        //select all columns from provider row that match id
        String sql = "SELECT * FROM provider WHERE ProviderID = ?";
        //established Connection
        Connection conn = connect();
        //creates obj
        PreparedStatement stmt = conn.prepareStatement(sql);
        //replaces ? in string with pro_id, creating valid sql Statement
        stmt.setString(1, pro_id);
        //queries appropriate table for Statement
        ResultSet results = stmt.executeQuery();
        results.next();
        //creates ProviderData obj
        ProviderData pro = new ProviderData();
        //populate data members of new object
        int address_id = results.getInt("AddressID");
        pro.address = AddressData.retrieve(address_id);
        pro.ident = Integer.parseInt(pro_id);
        pro.name = results.getString("Name");
        //pro.is_active = results.getBoolean("IsActive");
        conn.close();
        return pro;
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
            //write address first
            address.write();
            ident = get_next_ident();
            String[] vals = new String[]{
                    ident_to_string(ident),
                    name,
                    Boolean.toString(is_active),
                    Integer.toString(address.ident)
            };
            insert(table, columns, vals);
        }
        else
        {
            String sql = "UPDATE provider SET name = ? "
                    + "WHERE ProviderID = ?";
            address.write();
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, name);
                pstmt.setInt(2, ident);
                //UPDATE
                pstmt.executeUpdate();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
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
