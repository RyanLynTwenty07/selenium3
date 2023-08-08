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
        hotelsList.add(new HotelTitle("Radisson Hotel Danang","Phước Mỹ, Da Nang - 2.1 km to center"));
        hotelsList.add(new HotelTitle("SALA DANANG BEACH HOTEL","Phước Mỹ, Da Nang - 2.1 km to center"));
        hotelsList.add(new HotelTitle("Hilton Da Nang","Hải Châu, Da Nang - City center"));
        hotelsList.add(new HotelTitle("Fanta Suite Villa","Phước Mỹ, Da Nang - 1.9 km to center"));
        hotelsList.add(new HotelTitle("New Orient Hotel Da Nang","Hải Châu, Da Nang - City center"));
        return hotelsList;
    }
}


