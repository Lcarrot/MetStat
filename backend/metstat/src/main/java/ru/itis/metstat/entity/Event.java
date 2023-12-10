package ru.itis.metstat.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String page;
    private String tagName;
    private String className;
    private String textContent;
    private Long timestamp;
}
