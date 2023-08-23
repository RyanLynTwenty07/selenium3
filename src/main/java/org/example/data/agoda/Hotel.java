package org.example.data.agoda;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hotel {

    private String hotelName;
    private String destination;
    private double rating;
    private double price;
}


