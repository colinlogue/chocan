import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.sql.Timestamp;
import java.util.Vector;


public class SessionData extends DataSource {

    // private static data
    private static String table = "session";
    private static String[] columns = {
            "SessionID",
            "ProviderID",
            "MemberID",
            "ServiceCode",
            "ServiceDate",
            "LogTime",
            "Comments",
    };
    private static DateFormat date_format = new SimpleDateFormat("MM/dd/yyy");

    // public data
    public int member_id;
    public int provider_id;
    public int service_code;
    public Date date;
    public String comments;

    // db
    public static SessionData retrieve(int ident) throws SQLException {
        //selects all columns from member row that matches id
        return get("SessionID", Integer.toString(ident));
    }
    public static Vector<SessionData> retrieve_all(MemberData member) throws SQLException {
        String mem_id = ident_to_string(member.ident, 9);
        return get_all("MemberID", mem_id);
    }
    public static Vector<SessionData> retrieve_all(ProviderData provider) throws SQLException {
        String prov_id = ident_to_string(provider.ident, 9);
        return get_all("ProviderID", prov_id);
    }

    private static SessionData get(String column, String value) throws SQLException {
        String sql = "select * from session where " + column + " = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            try (ResultSet results = stmt.executeQuery();) {
                SessionData sess = new SessionData();
                sess.ident = results.getInt("SessionID");
                sess.member_id = results.getInt("MemberID");
                sess.provider_id = results.getInt("ProviderID");
                sess.service_code = results.getInt("ServiceCode");
                sess.date = results.getDate("ServiceDate");
                sess.comments = results.getString("Comments");
                return sess;
            }
        }
        catch (SQLException e) { throw e; }
    }

    private static Vector<SessionData> get_all(String column, String value) throws SQLException {
        String sql = "select * from session where " + column + " = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            try (ResultSet results = stmt.executeQuery();) {
                Vector<SessionData> vec = new Vector<SessionData>();
                while (results.next()) {
                    SessionData sess = new SessionData();
                    sess.ident = results.getInt("SessionID");
                    sess.member_id = results.getInt("MemberID");
                    sess.provider_id = results.getInt("ProviderID");
                    sess.service_code = results.getInt("ServiceCode");
                    sess.date = results.getDate("ServiceDate");
                    sess.comments = results.getString("Comments");
                    vec.add(sess);
                }
                return vec;
            }
        }
        catch (SQLException e) { throw e; }
    }


    public static Vector<SessionData> retrieve_all() throws SQLException {
        //selects all columns from member row that matches id
        String sql = "SELECT * FROM session";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            Vector<SessionData> vec = new Vector<SessionData>();
            while (results.next()) {
                SessionData sess = new SessionData();
                sess.ident = results.getInt("SessionID");
                sess.member_id = results.getInt("MemberID");
                sess.provider_id = results.getInt("ProviderID");
                sess.service_code = results.getInt("ServiceCode");
                sess.date = results.getDate("ServiceDate");
                sess.comments = results.getString("Comments");
                vec.add(sess);
            }
            return vec;
        }
        catch (SQLException e) { throw e; }
    }

    public boolean write() throws SQLException {
        // if no id, insert new row
        if (ident == 0) {
            String sql = "insert into session" +
                    "(SessionID,ProviderID,MemberID,ServiceCode,ServiceDate,LogTime,Comments) " +
                    "values (?,?,?,?,?,?,?)";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                ident = get_next_ident();
                pstmt.setInt(1,ident);
                pstmt.setString(2, ident_to_string(provider_id, 9));
                pstmt.setString(3, ident_to_string(member_id, 9));
                pstmt.setString(4, ident_to_string(service_code, 6));
                pstmt.setDate(5, date);
                pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                pstmt.setString(7, comments);
                pstmt.execute();
            }
            catch (SQLException e) {
                throw e;
            }
            return true;
        } else {
            // if has id, update row
            String sql = "update session set " +
                    "(ProviderID,MemberID,ServiceCode,ServiceDate,Comments) " +
                    "= (?,?,?,?,?) where SessionID = ?";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);) {
                pstmt.setString(1, ident_to_string(provider_id, 9));
                pstmt.setString(2, ident_to_string(member_id, 9));
                pstmt.setString(3, ident_to_string(service_code, 6));
                pstmt.setDate(4, date);
                pstmt.setString(5, comments);
                pstmt.setInt(6, ident);
                pstmt.execute();
            }
            catch (SQLException e) {
                throw e;
            }
            return true;
        }
    }

    private int get_next_ident() throws SQLException {
        // finds the previous max ident string and increments by 1
        String sql = "select max(SessionID) from session";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);)
        {
            return results.getInt(1) + 1;
        }
    }
    public void display() {
        System.out.println("\nSession " + Integer.toString(ident));
        System.out.println("Member ID:      " + Integer.toString(member_id));
        System.out.println("Provider ID:    " + Integer.toString(provider_id));
        System.out.println("Service code:   " + Integer.toString(service_code));
        System.out.println("Service date:   " + date_format.format(date));
        System.out.println("Comments:       " + comments);
        System.out.println();
    }

    public static void head() throws SQLException {
        String sql = "SELECT * FROM session";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(sql);) {
            System.out.println(" SessionID | MemberID   | ProviderID | ServiceCode | ServiceDate | Comments ");
            int i = 0;
            while (results.next() && i < 10) {
                StringBuilder row = new StringBuilder(" ");
                row.append(pad_to(results.getString("SessionID"), 12));
                row.append(pad_to(results.getString("MemberID"), 13));
                row.append(pad_to(results.getString("ProviderID"), 13));
                row.append(pad_to(results.getString("ServiceCode"), 14));
                row.append(pad_to(date_format.format(results.getDate("ServiceDate")), 14));
                row.append(results.getString("Comments"));
                System.out.println(row.toString());
                ++i;
            }
        }
        catch (SQLException e) { throw e; }
    }

    public static void tail() {

    }

    public static void main(String[] args) {
        try {
            MemberData mem = MemberData.retrieve(100002);
            Vector<SessionData> vec = SessionData.retrieve_all(mem);
            for (SessionData sess : vec) {
                sess.display();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // note: service code is ident (from DataSource base class)
}
