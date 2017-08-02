package exceptions;

public class fullVenue extends RuntimeException {
    public void printMessage(){
        System.out.println("Sorry, invalid number of seats ");
    }
}
