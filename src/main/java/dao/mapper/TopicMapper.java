package dao.mapper;

import dao.Constants;
import model.entities.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicMapper {

    public List<Topic> getTopicsFromResSet (ResultSet res) throws SQLException {
        List<Topic> topics = new ArrayList<>();

        while (res.next()) {
            int id = res.getInt(Constants.FIELD_ID);
            int eventId = res.getInt(Constants.FIELD_EVENT_ID);
            int speakerId = res.getInt(Constants.FIELD_ACCOUNT_ID);
            String name = res.getString(Constants.FIELD_NAME);
            String description = res.getString(Constants.FIELD_DESCRIPTION);
            boolean adminApproved = Boolean.parseBoolean(res.getString(Constants.ADMIN_APPROVED));
            boolean speakerApproved = Boolean.parseBoolean(res.getString(Constants.SPEAKER_APPROVED));
            boolean desigionDone = Boolean.parseBoolean(res.getString(Constants.DESIGION_IS_DONE));

            topics.add(new Topic(id, eventId, speakerId, name,
                    description, adminApproved, speakerApproved, desigionDone));
        }

        return topics;
    }
}
