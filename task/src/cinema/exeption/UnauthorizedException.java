package cinema.exeption;

public class UnauthorizedException extends BusinessLogicException{
    public UnauthorizedException() {
        super("Wrong token!");
    }
}
