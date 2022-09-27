package cinema.exeption;

public class AuthException extends RuntimeException{
    public AuthException() {
        super("The password is wrong!");
    }
}
