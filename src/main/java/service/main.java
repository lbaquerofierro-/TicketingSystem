package service;

import service.SeatHold;
import service.TicketServiceImp;

public class main {

    public static void main(String[] args){

        String reservationNum;
        Venue myVenue = new Venue(8, 6);
        TicketServiceImp service = new TicketServiceImp(myVenue);

        SeatHold holdSeats = service.findAndHoldSeats(3, "a@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "a@email.com");

        holdSeats = service.findAndHoldSeats(5, "a@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "a@email.com");

        holdSeats = service.findAndHoldSeats(13, "b@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "b@email.com");

        holdSeats = service.findAndHoldSeats(4, "c@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "c@email.com");

        holdSeats = service.findAndHoldSeats(1, "d@email.com");
        String e = service.reserveSeats(holdSeats.getSeatHoldId(), "d@email.com");

        holdSeats = service.findAndHoldSeats(2, "a@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "a@email.com");

        holdSeats = service.findAndHoldSeats(50, "a@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "a@email.com");

        holdSeats = service.findAndHoldSeats(0, "d@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "d@email.com");

        holdSeats = service.findAndHoldSeats(5, "a@email.com");
        reservationNum = service.reserveSeats(holdSeats.getSeatHoldId(), "a@email.com");
    }
}