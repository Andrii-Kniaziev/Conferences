package model.entities;

public class Topic {
    private int id;
    private int eventId;
    private int speakerId;
    private String name;
    private String description;
    private boolean adminApproved;
    private boolean speakerApproved;
    private boolean deсisionDone;

    public Topic(int id, int eventId, int speakerId, String name, String description,
                 boolean adminApproved, boolean speakerApproved, boolean deсisionDone) {
        this.id = id;
        this.eventId = eventId;
        this.speakerId = speakerId;
        this.name = name;
        this.description = description;
        this.adminApproved = adminApproved;
        this.speakerApproved = speakerApproved;
        this.deсisionDone = deсisionDone;
    }

    public Topic(int eventId, int speakerId, String name, String description, boolean adminApproved, boolean speakerApproved) {
        this.eventId = eventId;
        this.speakerId = speakerId;
        this.name = name;
        this.description = description;
        this.adminApproved = adminApproved;
        this.speakerApproved = speakerApproved;
        if(adminApproved && speakerApproved) {
            deсisionDone = true;
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
        return deсisionDone;
    }

    @Override
    public String toString() {
        return id + " " + eventId + " " + speakerId + " " + name;
    }
}
