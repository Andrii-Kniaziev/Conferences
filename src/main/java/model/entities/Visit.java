package model.entities;

public class Visit {
    private int accountID;
    private int eventID;
    private boolean was_present;

    public Visit(int accountID, int eventID) {
        this.accountID = accountID;
        this.eventID = eventID;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getEventID() {
        return eventID;
    }


}
