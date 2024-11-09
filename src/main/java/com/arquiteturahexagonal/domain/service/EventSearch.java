package com.arquiteturahexagonal.domain.service;

import com.arquiteturahexagonal.domain.entity.Event;

import java.util.List;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType){
        var parsedEvents = new ArrayList<Event>();
        unparsedEvents.forEach(event ->{
            parsedEvents.add(Event.parsedEvent(event, policyType));
        });
        return parsedEvents;
    }
}
