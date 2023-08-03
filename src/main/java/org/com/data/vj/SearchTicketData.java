package org.com.data.vj;

import lombok.Data;

@Data
public class SearchTicketData {

    private String ticketType;
    private String origination;
    private String destination;
    private String departureDate;
    private String returnDate;
    private String passenger;
    private String promoCode;
    private boolean lowestFare;

}
