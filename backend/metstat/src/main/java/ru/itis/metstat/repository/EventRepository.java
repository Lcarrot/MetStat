package ru.itis.metstat.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import ru.itis.metstat.entity.Event;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EventRepository {
    
    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_QUERY = "INSERT INTO pp.stat_metric(page, tagName, className, textContent, timestamp) VALUES (?, ?, ?, ?, ?);";
    public void saveNewEvents(Event[] events) {
        jdbcTemplate.batchUpdate(SAVE_QUERY,
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, events[i].getPage());
                ps.setString(2, events[i].getTagName());
                ps.setString(3, events[i].getClassName());
                ps.setString(4, events[i].getTextContent());
                ps.setLong(5, events[i].getTimestamp());
            }
            @Override
            public int getBatchSize() {
                return events.length;
            }
        });
    }
}
