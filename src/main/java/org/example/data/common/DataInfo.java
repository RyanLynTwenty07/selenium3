package org.example.data.common;

import lombok.Getter;
import org.com.common.Constants;
import org.example.data.vj.SearchTicketData;

import java.util.List;

public class DataInfo extends DataBase {

    public DataInfo() {
        super("");
    }

    public static List<SearchTicketData> getTicketData() {
        DataInfo data = new DataInfo();
        data.setJsonFile(Constants.VJ_SEARCH_DATA);
        data = data.to();
        List<SearchTicketData> customerInfo = data.getSearchTicketData();
        return customerInfo;
    }

    @Getter
    private List<SearchTicketData> searchTicketData;
}
