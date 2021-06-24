package model.entities;

public class Topic {
    private int id;
    private int eventId;
    private int speakerId;
    private String name;
    private String description;
    private boolean adminApproved;
    private boolean speakerApproved;
    private boolean decisionDone;

    public int getId() { return id; }

    public int getEventId() {
        return eventId;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAdminApproved() {
        return adminApproved;
    }

    public boolean isSpeakerApproved() {
        return speakerApproved;
    }

    public boolean isDeсisionDone() {
        return decisionDone;
    }

    public void setId(int id) { this.id = id; }

    public void setEventId(int eventId) { this.eventId = eventId; }

    public void setSpeakerId(int speakerId) { this.speakerId = speakerId; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setAdminApproved(boolean adminApproved) { this.adminApproved = adminApproved; }

    public void setSpeakerApproved(boolean speakerApproved) { this.speakerApproved = speakerApproved; }

    public void setDecisionDone(boolean decisionDone) { this.decisionDone = decisionDone; }

    @Override
    public String toString() {
        return id + " " + eventId + " " + speakerId + " " + name;
    }
}
