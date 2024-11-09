package com.arquiteturahexagonal.domain.policy;

import com.arquiteturahexagonal.domain.entity.Event;

import java.time.LocalDateTime;
import java.util.Arrays;

public final class SplitEventParser implements EventParser {
    @Override
    public Event parseEvent(String event) {
        var fields = Arrays.asList(event.split(" "));

        var timestamp = LocalDateTime.parse(fields.get(0), formatter).atOffset(ZoneOffset.UTC);
        var id = EventId.of(fields.get(1));
        var protocol = Protocol.valueOf(fields.get(2));
        var activity = new Activity(fields.get(3), fields.get(5));

        return new Event(timestamp,id, protocol, activity);
    }
}
