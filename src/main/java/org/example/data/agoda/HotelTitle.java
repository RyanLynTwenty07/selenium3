package org.example.data.agoda;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelTitle {

    private String hotelName;
    private String destination;
    private Double rating;
}
