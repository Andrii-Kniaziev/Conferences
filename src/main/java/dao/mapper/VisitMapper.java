package dao.mapper;

import dao.Constants;
import model.entities.Topic;
import model.entities.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitMapper {

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
