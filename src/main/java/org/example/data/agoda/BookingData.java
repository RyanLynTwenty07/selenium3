package org.example.data.agoda;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Data
public class BookingData {

    private String place;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfPeople;
    private int numberOfRooms;
    private int minPrice;
    private int maxPrice;
    private List<String> filters;

    public void getNextFridayAsBookingDate() {
        LocalDate today = LocalDate.now();
        checkInDate = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }

    public void setCheckOutDate(int checkOutIn) {
        checkOutDate = checkInDate.plusDays(checkOutIn);
    }
}