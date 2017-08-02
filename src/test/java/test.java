
import org.junit.Test;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Collection;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import service.SeatHold;
import service.TicketServiceImp;
import service.Venue;


public class test {

    private Integer numsSeats;
    private Venue testVenue;
    private TicketServiceImp service;
    private SeatHold holdSeats;

    InputStream stdin = System.in;

    @Before
    public void initialize() {
        testVenue = new Venue(8, 6);
        service = new TicketServiceImp(testVenue);
    }


    public void validFindHoldAndReservation(int seats, String email){
        holdSeats = service.findAndHoldSeats(seats, email);

        assertNotNull(holdSeats);
        assertNotNull(holdSeats.getHoldSeats());
        assertEquals(holdSeats.seatsHold(), seats);

        String ans = "y";
        stdin = System.in;
        System.setIn(new ByteArrayInputStream(ans.getBytes()));
        String result = service.reserveSeats(holdSeats.getSeatHoldId(), email);

        assertTrue(Integer.parseInt(result) >= 1000);
    }

    public void invalidFindHold(int seats, String email){
        holdSeats = service.findAndHoldSeats(seats, email);
        assertNotNull(holdSeats);
        assertNull(holdSeats.getHoldSeats());

        String ans = "y";
        stdin = System.in;
        System.setIn(new ByteArrayInputStream(ans.getBytes()));

        String result = service.reserveSeats(holdSeats.getSeatHoldId(),email);
        assertEquals(result, "");
    }

    @Test
    public void findHoldSeatsValid(){
        int seats;

        seats = getNumberOfSeats();
        assertEquals(seats, 48);

        validFindHoldAndReservation(3, "a@email.com");
        seats = getNumberOfSeats();
        assertEquals(seats, 45);

        validFindHoldAndReservation(5, "b@email.com");
        seats = getNumberOfSeats();
        assertEquals(seats, 40);

        validFindHoldAndReservation(13, "a@email.com");
        seats = getNumberOfSeats();
        assertEquals(seats, 27);

        validFindHoldAndReservation(4, "c@email.com");
        seats = getNumberOfSeats();
        assertEquals(seats, 23);

        validFindHoldAndReservation(1, "d@email.com");
        seats = getNumberOfSeats();
        assertEquals(seats, 22);

        validFindHoldAndReservation(2, "b@email.com");
        seats = getNumberOfSeats();
        assertEquals(seats, 20);
    }

    @Test
    public void findHoldSeatsInvalid(){
        int seats;

        invalidFindHold(0, "a@email.com");
        invalidFindHold(50, "b@email.com");
        invalidFindHold(-1, "c@email.com");

        seats = getNumberOfSeats();
        assertEquals(seats, 48);
    }

    public int getNumberOfSeats(){
        return testVenue.getAvailableSeats();
    }
}