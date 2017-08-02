package service;

import service.SeatHold;

public interface TicketService {
    public int numSeatsAvailable();
    SeatHold findAndHoldSeats(int numSeats, String customerEmail);
    String reserveSeats(int seatHoldId, String customerEmail);
}
