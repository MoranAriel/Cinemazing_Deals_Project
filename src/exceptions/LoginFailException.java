package exceptions;

public class LoginFailException extends Exception {
    public LoginFailException() {
        super("There is something wrong with your email and/or password!");
    }

    public LoginFailException(String message) {
        super(message);
    }
}
