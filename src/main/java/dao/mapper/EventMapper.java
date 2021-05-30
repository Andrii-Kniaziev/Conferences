package dao.mapper;

import dao.Constants;
import model.entities.Event;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class EventMapper {
    public List<Event> getEventsFromResultSet(ResultSet res) throws SQLException {
        List<Event> events = new ArrayList<>();

        while (res.next()) {
            int id = res.getInt(Constants.FIELD_ID);
            String name = res.getString(Constants.FIELD_NAME);
            String description = res.getString(Constants.FIELD_DESCRIPTION);
            Date date = new Date(res.getTimestamp(Constants.FIELD_DATE).getTime());

            String place = res.getString(Constants.FIELD_PLACE);
            boolean isFinished = Boolean.parseBoolean(res.getString(Constants.FIELD_IS_FINISHED));

            GregorianCalendar c = new GregorianCalendar();
            c.setTimeInMillis(date.getTime());

            events.add(new Event(id, name, description, c, place, isFinished));
        }

        return events;
    }

    public String getQueryForEventSort(String time, String sorting) {
        String res = "";
        switch (time + " " + sorting) {
            case "future date":
                res = Constants.GET_NOT_FINISHED_EVENTS_LIMIT;
                break;
            case "past date":
                res = Constants.GET_FINISHED_EVENTS_LIMIT;
                break;
            case "future topicNumber":
                res = Constants.GET_NOT_FINISHED_EVENTS_GROUP_BY_TOPICS;
                break;
            case "past topicNumber":
                res = Constants.GET_FINISHED_EVENTS_GROUP_BY_TOPICS;
                break;
            case "future listenersNumber":
                res = Constants.GET_NOT_FINISHED_EVENTS_GROUP_BY_VISITORS;
                break;
            case "past listenersNumber":
                res = Constants.GET_FINISHED_EVENTS_GROUP_BY_VISITORS;
                break;
        }
        return res;
    }
}
