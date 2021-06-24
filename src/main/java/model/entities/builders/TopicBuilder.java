package model.entities.builders;

import model.entities.Topic;

public interface TopicBuilder {

    TopicBuilder setId(int id);
    TopicBuilder setEventId(int eventId);
    TopicBuilder setSpeakerId(int speakerId);
    TopicBuilder setName(String name);
    TopicBuilder setDescription(String description);
    TopicBuilder setAdminApproved(boolean adminApproved);
    TopicBuilder setSpeakerApproved(boolean speakerApproved);
    TopicBuilder setDecisionDone(boolean decisionDone);

    Topic build();
}
