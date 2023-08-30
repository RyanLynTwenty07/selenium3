package org.example.data.common;

import org.com.report.Logger;
import org.example.data.vj.SearchTicketData;

import java.util.List;

public class DataFilter {
    public static SearchTicketData getSearchTicketData(String filterCode) {
        List<SearchTicketData> data = DataInfo.getTicketData();
        for (SearchTicketData e : data) {
            if (e.getFilterCode().equals(filterCode)) {
                return e;
            }
        }
        Logger.info("There are no data get from Json");
        return null;
    }
}
