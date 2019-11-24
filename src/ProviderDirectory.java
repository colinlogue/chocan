/*

Database access class for services.

 */

public class ProviderDirectory extends DataSource {

    private int ident_len;

    private String ident_to_string(int num) {
        return DataSource.ident_to_string(num, ident_len);
    }

    public void display() {

    }
}
