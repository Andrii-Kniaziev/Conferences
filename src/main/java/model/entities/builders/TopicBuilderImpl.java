package model.entities.builders;

import model.entities.Topic;

public class TopicBuilderImpl implements TopicBuilder {
    private Topic topic = new Topic();

    @Override
    public TopicBuilder setId(int id) {
        topic.setId(id);
        return this;
    }

    @Override
    public TopicBuilder setEventId(int eventId) {
        topic.setEventId(eventId);
        return this;
    }

    @Override
    public TopicBuilder setSpeakerId(int speakerId) {
        topic.setSpeakerId(speakerId);
        return this;
    }

    @Override
    public TopicBuilder setName(String name) {
        topic.setName(name);
        return this;
    }

    @Override
    public TopicBuilder setDescription(String description) {
        topic.setDescription(description);
        return this;
    }

    @Override
    public TopicBuilder setAdminApproved(boolean adminApproved) {
        topic.setAdminApproved(adminApproved);
        return this;
    }

    @Override
    public TopicBuilder setSpeakerApproved(boolean speakerApproved) {
        topic.setSpeakerApproved(speakerApproved);
        return this;
    }

    @Override
    public TopicBuilder setDecisionDone(boolean decisionDone) {
        topic.setDecisionDone(decisionDone);
        return this;
    }

    @Override
    public Topic build() {
        return topic;
    }
}
