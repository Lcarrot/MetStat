package ru.itis.metstat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementCounterWrapper {

    private List<ElementCounter> data;
}
