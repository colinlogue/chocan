import java.sql.SQLException;

public class ProviderData extends PersonData {

    public static ProviderData retrieve(int ident) {
        return new ProviderData();
    }

    public static void delete(int ident) throws SQLException {
        // drop row matching ident from provider table
    }

    public void display() {
    }

    public boolean write() throws SQLException {
        return true;
    }

}
