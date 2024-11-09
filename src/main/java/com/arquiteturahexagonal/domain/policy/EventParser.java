package com.arquiteturahexagonal.domain.policy;

import com.arquiteturahexagonal.domain.entity.Event;

import java.time.format.DateTimeFormatter;

public sealed interface EventParser permits RegexEventParser, SplitEventParser {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("UTC"));

    Event parseEvent(String event);
}

