package ru.itis.metstat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.metstat.entity.ElementCounter;
import ru.itis.metstat.entity.ElementCounterWrapper;
import ru.itis.metstat.repository.EventRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetEventsController {

    private final EventRepository eventRepository;

    @GetMapping("/tags")
    @CrossOrigin
    public ElementCounterWrapper getTagsCount(@RequestParam("timeOffset") Long timeOffset) {
        List<ElementCounter> counters = eventRepository.getTagsCount(timeOffset);
        return new ElementCounterWrapper(counters);
    }

    @GetMapping("/clicks")
    @CrossOrigin
    public ElementCounterWrapper getTextCount(@RequestParam("timeOffset") Long timeOffset) {
        List<ElementCounter> counters = eventRepository.getClickCount(timeOffset);
        return new ElementCounterWrapper(counters);
    }
}
