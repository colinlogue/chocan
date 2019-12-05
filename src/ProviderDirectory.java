/*
kelsey: This should contain an alphabetically ordered list of service names
 with corresponding service codes and fees.
 The provider terminal will access this directory to ask for a service
 and its info.

 */

public class ProviderDirectory extends DataSource {

    private int ident_len;

    private String ident_to_string(int num) {
        return DataSource.ident_to_string(num, ident_len);
    }

    public void display() {

    }
}
