import java.sql.*;

public class MemberData extends PersonData {

    public MemberData(){
       super();
    }

    //Constructor used for testing. Please don't remove
    public MemberData(String new_name, String new_street, String new_city, String new_state, String new_zip) {
        super(new_name, new_street, new_city, new_state, new_zip);
    }

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

        String mem_id = ident_to_string(ident);
        //String mem_id = Integer.toString(ident);
        String sql = "SELECT * FROM member WHERE MemberID = ?";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            MemberData mem = new MemberData();
            mem.ident = results.getInt("MemberID");
            int address_id = results.getInt("AddressID");
            mem.address = AddressData.retrieve(address_id);
            mem.name = results.getString("Name");
            mem.is_active = results.getBoolean("IsActive");
            return mem;
        }
        catch (SQLException e) { throw e; }

    }

    public boolean write() throws SQLException {
        // if no id, new row
        if (ident == 0){
            String sql = "INSERT INTO member" +
                        "(MemberID,Name,IsActive,AddressID) " +
                        "values (?,?,?,?)";
            address.write();
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                ident = get_next_ident();
                pstmt.setInt(1,ident);
                pstmt.setString(2, name);
                String act  = Boolean.toString(is_active);
                pstmt.setString(3,act);
                String add_id =  Integer.toString(address.ident);
                pstmt.setString(4, add_id);
                pstmt.execute();
            }
            catch (SQLException e) {
                throw e;
            }
            return true;
        } else {
            String sql = "UPDATE member SET " +
                        "(Name,IsActive,AddressID) " +
                        "= (?,?,?) WHERE MemberID = ?";
            address.write();
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setString(1, name);
                String act  = Boolean.toString(is_active);
                pstmt.setString(2,act);
                String add_id =  Integer.toString(address.ident);
                pstmt.setString(3, add_id);
                pstmt.setInt(4,ident);
                pstmt.execute();
            }
            catch (SQLException e) {
                throw e;
            }
        }
        return true;
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
