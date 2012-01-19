/**
*    Class create exception BadFileException.
*    This exception calls, when we can't open db.
*/
public class  BadFileException extends Exception {
    public static final long serialVersionUID = 12351233224l;
    public BadFileException() {
        super();
    }
    public BadFileException(String info) {
        super(info);
    }
    public BadFileException(String message, Throwable cause) {
        super (message, cause);
    }
    public BadFileException(Throwable cause) {
        super(cause);
    }
}