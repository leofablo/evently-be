package com.pwdk.minpro_be.event.service.Impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.pwdk.minpro_be.event.dto.CreateEventDto;
import com.pwdk.minpro_be.event.entity.Event;
import com.pwdk.minpro_be.event.helper.SlugGen;
import com.pwdk.minpro_be.event.repository.EventRepository;
import com.pwdk.minpro_be.event.service.EventService;
import com.pwdk.minpro_be.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final SlugGen slugGen;

    public EventServiceImpl(EventRepository eventRepository, SlugGen slugGen) {
        this.eventRepository = eventRepository;
        this.slugGen = slugGen;
    }


    @Override
    public Event createEvent(CreateEventDto event) {
        Event newEvent = event.toEntity();
        String eventSlug = slugGen.slugGenerator(event.getName());
        var isSlugExist = eventRepository.findBySlug(eventSlug);
        if (isSlugExist.isPresent()) {
            var random = new Random();
            char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
            eventSlug = eventSlug + NanoIdUtils.randomNanoId(random, alphabet, 5);
            newEvent.setSlug(eventSlug);
        }
        newEvent.setSlug(eventSlug);
        return eventRepository.save(newEvent);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }


    @Override
    public void deleted_at(Long Id) {

    }

    @Override
    public Event findByUser(Event event) {
        return null;
    }

    @Override
    public Event findBySlug(String eventSlug) {
        Optional<Event> event = eventRepository.findBySlug(eventSlug);
        if (event.isEmpty()) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Event not found");
        }
        return event.get();
    }


}
