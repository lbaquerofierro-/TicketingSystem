package service;

public class Seat {

    int row, col;
    Status status;

    public enum Status {
        AVAILABLE, RESERVED, HOLD
    }

    Seat(int r, int c){
        row = r;
        col = c;
        status = Status.AVAILABLE;
    }

    public void updateSeatStatus(Status seatStatus){
        this.status = seatStatus;
    }
}
