// This is the base class for accessing the data for people, i.e.
// members and providers.

public class PersonData extends DataSource {
    public enum status {
        INVALID,        // if id is not in db
        VALID,          // if all is good
        SUSPENDED,      // if member hasn't paid dues
    }

    // public data
    public String name;
    public AddressData address;

    // private data
    private int ident;

    // static methods
    public static status validate(int ident) {
        // TODO
        return status.VALID;
    }

    // public methods
    public boolean write() {
        // writes data to the db
        // TODO
        return true;
    }

    public void display() {
        // displays data to the console
        // TODO
    }
}
