package org.example.data.agoda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class HotelTitle {

    private String hotelName;
    private String destination;

    public ArrayList<HotelTitle> returnListHotelRec() {
        ArrayList<HotelTitle> hotelsList = new ArrayList<>();
        hotelsList.add(new HotelTitle("The Grumpy House","Phước Mỹ, Da Nang - 2.1 km to center"));
        hotelsList.add(new HotelTitle("Grand Sunrise 3 Hotel & Spa Da Nang","Phước Mỹ, Da Nang - 2.1 km to center"));
        hotelsList.add(new HotelTitle("NHA TRO TYTY","Phước Mỹ, Da Nang - 2.1 km to center"));
        hotelsList.add(new HotelTitle("Si House","Hòa Hải, Da Nang - 3.7 km to center"));
        hotelsList.add(new HotelTitle("nhà nghỉ family","Lang Co, Da Nang - 36.5 km to center"));
        return hotelsList;
    }
}


