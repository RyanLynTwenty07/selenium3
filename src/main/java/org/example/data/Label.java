package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Label {

    RETURN("Return","Khứ hồi"),
    LOWEST_FARE("Find lowest fare","Tìm vé rẻ nhất"),
    LETS_GO("Let's go","Tìm chuyến bay"),
    CONTINUE("Continue","Đi tiếp"),
    DEPARTURE_DATE("Departure date","Ngày đi"),
    ACCEPT("Accept","Đồng ý"),
    ADULTS("Adults","Người lớn"),
    PASSENGER("Passenger","Hành khách"),
    PASSENGER_INFORMATION("Passenger information","Thông tin hành khách"),
    ONEWAY("One-way","Một chiều");

    private String english;
    private String vietnam;
}
