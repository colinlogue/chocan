import java.util.Date;

public class SessionData extends DataSource {

    // public data
    public int member_id;
    public int provider_id;
    public int service_code;
    public Date date;
    public String comments;

    // db
    public static SessionData retrieve(int ident) {
        return new SessionData();
    }

    // note: service code is ident (from DataSource base class)
}
