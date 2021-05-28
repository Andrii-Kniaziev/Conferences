package model.entities;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Event {
    private int id;
    private String name;
    private String description;
    private GregorianCalendar calendar;
    private String place;
    private boolean isFinished;
    private String formattedDate;
    private String finished;

    public Event(String name, String description, GregorianCalendar calendar, String place, boolean isFinished) {
        this.name = name;
        this.description = description;
        this.calendar = calendar;
        this.place = place;
        this.isFinished = isFinished;
        formattedDate = getFormattedDate();
        finished = getStatus();
    }

    public Event(int id, String name, String description, GregorianCalendar calendar, String place, boolean isFinished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calendar = calendar;
        this.place = place;
        this.isFinished = isFinished;
        formattedDate = getFormattedDate();
        finished = getStatus();
    }

    public String getStatus() {
        if (isFinished) {
            return "finished";
        }
        return "not finished";
    }

    public String getFormattedDate() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formater.format(calendar.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + description + " " + getFormattedDate() + " " + place + " " + isFinished;
    }
}
