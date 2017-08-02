package exceptions;

public class nullSeats extends RuntimeException {
    public void printMessage(){
        System.out.println("Sorry, not enough seats available ");
    }
}
