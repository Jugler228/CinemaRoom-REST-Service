package cinema.exeption;


public class AlreadySoldException extends BusinessLogicException{
    public AlreadySoldException(){
        super("The ticket has been already purchased!");
    }
}
