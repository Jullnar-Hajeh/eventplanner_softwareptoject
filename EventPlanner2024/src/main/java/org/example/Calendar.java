package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Calendar {
    private List<Event> events;
    private int year;
    private int month;

    public Calendar() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public Event getEventById(String eventId) {
        return events.stream()
                .filter(event -> event.getEID().equals(eventId))
                .findFirst()
                .orElse(null);
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    
    public void deleteEvent(Event event) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event currentEvent = iterator.next();
            if (currentEvent.equals(event)) {
                iterator.remove();
                break; // Assuming each event has a unique identifier
            }
        }
}
    
    
    
    
    
    
    
    
    
    
}