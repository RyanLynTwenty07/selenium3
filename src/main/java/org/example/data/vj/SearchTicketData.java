package org.example.data.vj;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchTicketData {

    private String ticketType;
    private String filterCode;
    private String origination;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Passenger passenger;
    private String promoCode;
    private boolean lowestFare;

}
