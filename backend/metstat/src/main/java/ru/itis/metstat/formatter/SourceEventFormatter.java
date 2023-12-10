package ru.itis.metstat.formatter;

import ru.itis.metstat.entity.Event;

import java.util.List;

public class SourceEventFormatter {

    public static void formatSourceEvent(List<Event> events) {
        for (Event event: events) {
            event.setPage(event.getPage().replaceAll("/", ""));
        }
    }
}
