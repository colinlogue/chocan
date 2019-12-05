/*
 grab data from DB
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProviderDirectory extends DataSource {

    // Looks up a
    // Throws a SQLException if the service_code is not found
    public static Service lookup(int service_code) throws SQLException {
        String sql = "select * from service where ServiceCode = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, service_code);
            try (ResultSet results = pstmt.executeQuery()) {
                Service service = new Service();
                service.fee = results.getInt("fee");
                service.label = results.getString("label");
                service.service_code = service_code;
                return service;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public void display() {
        System.out.print("Thank you. An provider directory has been sent to the email address associated with your Provider ID");

    }

    public static void main(String[] args) {
        try {
            Service s = ProviderDirectory.lookup(103050);
            System.out.println(s.label);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

