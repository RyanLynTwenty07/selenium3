package org.example.data.agoda;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Data
public class SearchData {

    private String place;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfPeople;
    private int numberOfRooms;
    private double minPrice;
    private double maxPrice;
    private List<String> filters;

    public void getNextFridayAsBookingDate() {
        LocalDate today = LocalDate.now();
        checkInDate = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }

    public void setCheckInDate(int checkIn){
        checkInDate = checkInDate.now().plusDays(checkIn);
    }
    public void setCheckOutDate(int checkOutIn) {
        checkOutDate = checkOutDate.now().plusDays(checkOutIn);
    }
}