package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {

    private int id;
    private String email;
    private ArrayList<Map<Integer, SeatHold>> customerReservations;

    Customer(String customerEmail) {
        email = customerEmail;
        customerReservations = new ArrayList<Map<Integer, SeatHold>>();
    }

    public void addToReservationList(int reservationId, SeatHold seats) {
        Map<Integer, SeatHold> reserv = new HashMap<Integer, SeatHold>();
        reserv.put(reservationId, seats);
        customerReservations.add(reserv);
    }

}