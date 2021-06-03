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

    public Topic(int id, int eventId, int speakerId, String name, String description,
                 boolean adminApproved, boolean speakerApproved, boolean decisionDone) {
        this.id = id;
        this.eventId = eventId;
        this.speakerId = speakerId;
        this.name = name;
        this.description = description;
        this.adminApproved = adminApproved;
        this.speakerApproved = speakerApproved;
        this.decisionDone = decisionDone;
    }

    public Topic(int eventId, int speakerId, String name, String description,
                 boolean adminApproved, boolean speakerApproved, boolean decisionDone) {
        this.eventId = eventId;
        this.speakerId = speakerId;
        this.name = name;
        this.description = description;
        this.adminApproved = adminApproved;
        this.speakerApproved = speakerApproved;
        this.decisionDone = decisionDone;
    }

    public Topic(int eventId, int speakerId, String name, String description, boolean adminApproved, boolean speakerApproved) {
        this.eventId = eventId;
        this.speakerId = speakerId;
        this.name = name;
        this.description = description;
        this.adminApproved = adminApproved;
        this.speakerApproved = speakerApproved;
        if(adminApproved && speakerApproved) {
            decisionDone = true;
        }
    }

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

    @Override
    public String toString() {
        return id + " " + eventId + " " + speakerId + " " + name;
    }
}
