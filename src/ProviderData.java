import java.sql.*;
/*
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/
public class ProviderData extends PersonData {

    private static String tabel = "provider";
    private static String[] columns = {
        "ProviderID",
        "Name",
        "AddressID"
    };

    //public boolean is_active;   //lift from MemberData?

    public static ProviderData retrieve(int ident) throws SQLException {
        //convert int to String
        String pro_id = ident_to_string(ident);
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
        //creates ProviderData obj
        ProviderData pro = new ProviderData();
        //populate data members of new object
        int address_id = results.getInt("ProviderID");
        pro.address = AddressData.retrieve(address_id);
        pro.ident = Integer.parseInt(pro_id);
        //pro.is_active = results.getBoolean("IsActive");
        conn.close();
        return pro;
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
