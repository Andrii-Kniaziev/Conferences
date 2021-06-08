package dao.mapper;

import model.entities.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitMapper {

    /**
     * Method extracts data from ResultSet and creates
     * objects of Visit class for further processing
     * @param res ResultSet
     * @return list of Visit class objects
     * @throws SQLException in case of errors
     */

    public List<Visit> getVisitsFromResSet(ResultSet res) throws SQLException {
        List<Visit> visits = new ArrayList<>();

        while (res.next()) {
            int accountId = res.getInt("account_id");
            int eventId = res.getInt("event_id");


            visits.add(new Visit(accountId, eventId));
        }

        return visits;
    }
}
