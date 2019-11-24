import java.sql.*;

public class MemberData extends PersonData {

    private static String table = "member";
    private static String[] columns = {
            "MemberID",
            "Name",
            "IsActive",
            "AddressID"
    };

    public boolean is_active;

    // db access
    public static MemberData retrieve(int ident) throws SQLException {
        //convert int to string
        String mem_id = ident_to_string(ident);
        //selects all columns from member row that matches id
        String sql = "SELECT * FROM member WHERE MemberID = ?";
        //establishes connection
        //consider turning this into try/catch
        Connection conn = connect();
        //creates obj
        PreparedStatement stmt = conn.prepareStatement(sql);
        //replaces ? in string with mem_id, creating a valid SQL statement
        stmt.setString(1, mem_id);
        //queries appropriate table for statement
        ResultSet results = stmt.executeQuery();
        //creates MemberData obj
        MemberData mem = new MemberData();
        //populates data members
        int address_id = results.getInt("AddressID");
        mem.address = AddressData.retrieve(address_id);
        mem.ident = Integer.parseInt(mem_id);
        mem.name = results.getString("Name");
        mem.is_active = results.getBoolean("IsActive");
        conn.close();
        return mem;
    }

    public boolean write() throws SQLException {
        // write address first
        address.write();
        // if no id, insert new row
        if (ident == 0) {
            ident = get_next_ident();
            String[] vals = new String[] {
                    ident_to_string(ident),
                    name,
                    Boolean.toString(is_active),
                    Integer.toString(address.ident)
            };
            insert(table, columns, vals);
        }
        //else if
        //update existing row code goes here
        return true;
        //add return false for failure
    }

    private int get_next_ident() throws SQLException {
        // finds the previous max ident string and increments by 1
        String sql = "select max(MemberID) from member";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);)
        {
            return Integer.parseInt(results.getString(1)) + 1;
        }
    }

    // test
    public static void main(String[] args) {
        try {
            MemberData mem = new MemberData();
            mem.name = "Cahoots McDoinkel";
            mem.address = new AddressData();
            mem.is_active = true;
            mem.write();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
