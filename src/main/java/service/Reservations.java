package service;

import service.Customer;

import java.util.Map;
import java.util.HashMap;

public class Reservations {

    private static int reservationId = 1000;
    private Map<String, Customer> customersReservations = new HashMap<String, Customer>();

    public int addCustomerReservation(String customerEmail, SeatHold holdSeats){

        if(customersReservations.get(customerEmail) == null){
            Customer newCustomer = new Customer(customerEmail);
            customersReservations.put(customerEmail, newCustomer);
        }

        Customer aCustomer = customersReservations.get(customerEmail);
        aCustomer.addToReservationList(reservationId, holdSeats);

        return reservationId++;
    }
}
