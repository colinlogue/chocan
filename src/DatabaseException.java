public class DatabaseException extends Exception {

    // data members
    private String message;
    private Throwable cause;

    // constructors
    public DatabaseException(String message) {
        super(message);
    }
    public DatabaseException(Throwable cause) {
        super(cause);
    }
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
