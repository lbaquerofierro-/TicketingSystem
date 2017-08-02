package service;

import java.util.ArrayList;
import java.sql.Timestamp;

public class SeatHold {

    private int seatHoldId = 0;
    private ArrayList<Seat> holdSeats; //public?private
    private Timestamp timestamp;


    public SeatHold(){
        seatHoldId++;
    }

    public int getSeatHoldId(){
        return seatHoldId;
    }

    private void setTimeStamp(){
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void addSeats(int startRowNum, int endRowNum, int colMin, int colMax, int numSeats, Venue myVenue){

        int min, max;
        holdSeats = new ArrayList<Seat>(numSeats);

        for(int i = startRowNum; i <= endRowNum; i++){

            if(i == startRowNum) min = colMin;
            else min = 0;

            if(i == endRowNum) max = colMax;
            else max = myVenue.getNumberOfCols() - 1; //-1 to account for 0 index

            for(int j = min; j <= max; j++){
                Seat hold = myVenue.getVenueSeats()[i][j];
                if(hold.status == Seat.Status.AVAILABLE) {
                    hold.status = Seat.Status.HOLD;
                    holdSeats.add(hold);
                }
            }
        }

        setTimeStamp();
    }

    public void changeStatus(){
        for(int i = 0; i < holdSeats.size(); i++){
            holdSeats.get(i).status = Seat.Status.RESERVED;
        }
    }

    public ArrayList<Seat> getHoldSeats() {
        return holdSeats;
    }

    public int seatsHold(){
        return holdSeats.size();
    }

}
