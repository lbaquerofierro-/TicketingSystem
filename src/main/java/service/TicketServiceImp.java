package service;

import exceptions.fullVenue;
import org.junit.runners.Parameterized;
import service.Reservations;
import service.SeatHold;

import java.sql.Timestamp;
import java.util.*;
import java.io.*;

public class TicketServiceImp implements TicketService {
    private Venue venue;
    private int rows;
    private int cols;
    private int startRow, minCol;
    //Scanner scan = new Scanner(System.in);
    Reservations reserv = new Reservations();

    private Map<String, SeatHold> seatsOnHold = new HashMap<String, SeatHold>();

    public TicketServiceImp(Venue myVenue) {
        venue = myVenue;
        rows = venue.getNumberOfRows();
        cols = venue.getNumberOfCols();
    }

    //@Override
    public int numSeatsAvailable() {
        return venue.getAvailableSeats();
    }

    //@Override
    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) throws fullVenue {

        int endRow, maxCol;
        int emptySeatsInFirstRow;
        SeatHold holdSeats = new SeatHold();

        try{
            if (numSeats > numSeatsAvailable() || numSeats <= 0) {
                throw new fullVenue();
            }else{
                startRow = venue.getFirstRowWithEmptySeats();
                minCol = venue.getColOfFirstEmptySeat();
                emptySeatsInFirstRow = (cols - minCol) - numSeats;


                if (emptySeatsInFirstRow >= 0) {
                    endRow = startRow;
                    maxCol = (minCol + numSeats) - 1;
                } else {
                    endRow = Math.abs((emptySeatsInFirstRow) / cols) + startRow + 1;
                    maxCol = (Math.abs(emptySeatsInFirstRow % cols)) - 1; //-1 to account for 0 index
                }

                System.out.println(
                        "The seats that will be reserved are " +
                                getCharForNumber(startRow + 1) +
                                (minCol + 1) + " to " +
                                getCharForNumber(endRow + 1) +
                                (maxCol + 1)
                );


                holdSeats.addSeats(startRow, endRow, minCol, maxCol, numSeats, venue);

                venue.updateColOfFirstEmptySeat(maxCol, false);
                venue.updateFirstRowWithEmptySeats(endRow, false);
                venue.updateAvailableSeats(numSeats, false);
                seatsOnHold.put(customerEmail, holdSeats);

                return holdSeats;
            }
        }catch (fullVenue e){
            e.printMessage();
        }

        return holdSeats;
    }

    //@Override

    public String reserveSeats(int seatHoldId, String customerEmail) {
        int reservId = 0;
        long secondsPassed;
        String ans;
        String reservationConf = "";
        SeatHold holdSeats;
        Timestamp now;

        Scanner scan = new Scanner(System.in);

        try{
            holdSeats = seatsOnHold.get(customerEmail);
            now = new Timestamp(System.currentTimeMillis());
            secondsPassed = now.getTime() - holdSeats.getTimestamp().getTime();

            System.out.println("Confirm reservation (y/n): ");
            ans = scan.nextLine();

            if(ans.equals("y") && secondsPassed > 12000 || ans.equals("n")){
                //Cancel reservation

                if(ans.equals("n")) System.out.println("Reservation canceled.");
                else System.out.println("Sorry, your ticket reservation has expired. Please try again later.");

                seatsOnHold.remove(customerEmail);

                venue.updateAvailableSeats(holdSeats.getHoldSeats().size(), true);
                venue.updateColOfFirstEmptySeat(minCol, true);
                venue.updateFirstRowWithEmptySeats(startRow, true);
            }else{
                //Make reservation
                holdSeats.changeStatus();
                reservId = reserv.addCustomerReservation(customerEmail, holdSeats);
                reservationConf = String.valueOf(reservId);
                System.out.println("Reservation confirmation number: " + reservationConf);
            }

            seatsOnHold.clear();
        }catch (NullPointerException e){
            seatsOnHold.clear();
        }

        return reservationConf;
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }
}
