package ru.itis.metstat.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ru.itis.metstat.entity.ElementCounter;
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

    public static final String GET_TAGS_QUERY = "select tagName, count(tagName) as count from pp.stat_metric where timestamp > ? group by tagName;";

    public List<ElementCounter> getTagsCount(Long timestamp) {
        return jdbcTemplate.query(
                con -> {
                    PreparedStatement statement = con.prepareStatement(GET_TAGS_QUERY);
                    statement.setLong(1, timestamp);
                    return statement;
                },
                (rs, rowNum) -> new ElementCounter(rs.getString("tagName"), rs.getLong("count")));
    }

    public static final String GET_COUNT_BY_TIMESTAMP_QUERY = "select count(*) as count, toStartOfDay(fromUnixTimestamp(intDiv(timestamp, 1000))) as date from pp.stat_metric where timestamp > ? group by date order by date;";

    public List<ElementCounter> getClickCount(Long timestamp) {
        return jdbcTemplate.query(
                con -> {
                    PreparedStatement statement = con.prepareStatement(GET_COUNT_BY_TIMESTAMP_QUERY);
                    statement.setLong(1, timestamp);
                    return statement;
                },
                (rs, rowNum) -> new ElementCounter(rs.getString("date"), rs.getLong("count")));
    }
}
