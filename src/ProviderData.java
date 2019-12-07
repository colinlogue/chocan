import java.sql.*;

public class ProviderData extends PersonData {

    public ProviderData(){
        super();
    }

    //Constructor used for testing. Please don't remove
    public ProviderData(String new_name, String new_street, String new_city, String new_state, String new_zip) {
        super(new_name, new_street, new_city, new_state, new_zip);
    }
    public ProviderData(String new_name, String new_street, String new_city, String new_state, String new_zip, int new_id) {
        super(new_name, new_street, new_city, new_state, new_zip);
        ident = new_id;
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
        int pro_id = ident;
        String sql = "SELECT * FROM provider WHERE ProviderID = " + pro_id;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            ProviderData pro = new ProviderData();
            int address_id = results.getInt("AddressID");
            pro.address = AddressData.retrieve(address_id);
            pro.ident = results.getInt("ProviderID");
            pro.name = results.getString("Name");
            conn.close();
            return pro;
        }
        catch (SQLException e) { throw e; }

    }


    /*public void display() {
        System.out.println(name);
    }*/

    public static PersonData.status validate(int ident) throws SQLException {
        String sql = "select * from provider where ProviderID = " + ident;
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
                //String add_id =  Integer.toString(address.ident);
                //pstmt.setString(3, add_id);
                pstmt.setInt(3,address.ident);
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
            return results.getInt(1) + 1;
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

    public static boolean delete(int ID) throws SQLException {
        if (ProviderData.validate(ID) == status.INVALID) {
            return false;
        }
        else {

            String sql = "UPDATE provider SET " +
                    "(IsHidden) " +
                    "= ? WHERE ProviderID = ?";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setBoolean(1, true);
                pstmt.setInt(2, ID);
                pstmt.execute();
                return true;
            } catch (SQLException e) {
                throw e;
            }
        }
    }


    public static void display_ids() {

    }

    public static void retrieve_all() throws SQLException {
        String sql = "SELECT *  FROM provider";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            while (results.next()) {
                ProviderData pro = new ProviderData();
                pro.ident = results.getInt("ProviderID");
                pro.name = results.getString("Name");
              //  mem.is_active = results.getBoolean("IsActive");
                boolean hidden = results.getBoolean("IsHidden");
                if(hidden == false){
                    pro.display_name_id();
                }
            }
        }
        catch (SQLException e) { throw e; }
    }
}
