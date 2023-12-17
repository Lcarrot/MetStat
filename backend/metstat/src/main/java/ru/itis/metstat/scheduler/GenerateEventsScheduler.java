package ru.itis.metstat.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.metstat.entity.Event;
import ru.itis.metstat.repository.EventRepository;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class GenerateEventsScheduler {

    private static final long OFFSET = 2592000000L;
    private static final int BATCH_SIZE = 1_000;
    private static final String[] TAGS = {"INPUT", "A", "DIV", "IMG", "BUTTON", "P"};

    private final EventRepository eventRepository;

//    @Scheduled(fixedDelay = 1000)
    public void generateEvents() {
        long current = new Date().getTime();
        long start = current - OFFSET;
        Event[] events = new Event[BATCH_SIZE];
        for (int i = 0; i < BATCH_SIZE; i++) {
            Long timestamp = ThreadLocalRandom.current().nextLong(start, current);
            Event newEvent = Event.builder()
                    .timestamp(timestamp)
                    .page("index.html")
                    .className(generateStringValue())
                    .tagName(getRandomTag())
                    .textContent(generateTextContent())
                    .build();
            events[i] = newEvent;
        }
        eventRepository.saveNewEvents(events);
    }

    private static String generateStringValue() {
        int size = ThreadLocalRandom.current().nextInt(15);
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            char symbol = (char) ThreadLocalRandom.current().nextInt('a', 'z');
            builder.append(symbol);
        }
        return builder.toString();
    }

    private static String getRandomTag() {
        int element = ThreadLocalRandom.current().nextInt(TAGS.length);
        return TAGS[element];
    }

    private static String generateTextContent() {
        int wordsCount = ThreadLocalRandom.current().nextInt(15);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < wordsCount; i++) {
            builder.append(generateStringValue()).append(" ");
        }
        return builder.toString();
    }
}
