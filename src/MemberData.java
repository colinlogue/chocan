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

    public static status validate(int ident) throws SQLException {
        String sql = "select * from member where MemberID = " + ident_to_string(ident);
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            if (results.next()) {
                if (results.getBoolean("IsActive")) {
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
            throw e;
        }
    }



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
        results.next();
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

    public static void delete(int ident) throws SQLException {
        // drop row matching ident from member table
        String mem_id = ident_to_string(ident);
        String sql = "DELETE FROM member WHERE MemberID = " + mem_id;

        try (Connection conn = connect(); //check this **
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, ident);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //possibly add to base class PersonData **
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

            PersonData.status valid = MemberData.validate(100002);
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
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
