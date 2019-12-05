/*
 grab data from DB
 */

public class ProviderDirectory extends DataSource {

    private int ident_len;

    private String ident_to_string(int num) {
        return DataSource.ident_to_string(num, ident_len);
    }

    public void display() {
        System.out.print("Thank you. An provider directory has been sent to the email address associated with your Provider ID");

    }
}
