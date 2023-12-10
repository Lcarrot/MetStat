package ru.itis.metstat.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import ru.itis.metstat.entity.*;
import lombok.RequiredArgsConstructor;
import ru.itis.metstat.formatter.SourceEventFormatter;
import ru.itis.metstat.repository.EventRepository;


@RestController
@RequiredArgsConstructor
public class AddEventController {

    private final EventRepository eventRepository;

    @CrossOrigin
    @PostMapping("/addEvents")
    public void addEvents(@RequestBody AddEvent addEvent) {
        SourceEventFormatter.formatSourceEvent(addEvent.getEvents());
        eventRepository.saveNewEvents(addEvent.getEvents().toArray(Event[]::new));
    }

}