package model.entities;

public class Visit {
    private int accountId;
    private int eventId;

    public Visit(int accountId, int eventId) {
        this.accountId = accountId;
        this.eventId = eventId;
    }

    public int getAccountID() {
        return accountId;
    }

    public int getEventID() {
        return eventId;
    }


}
