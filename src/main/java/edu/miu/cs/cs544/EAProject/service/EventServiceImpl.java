package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.advice.EventNotFoundException;
import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

public class EventServiceImpl implements EventService{

    @Autowired
    EventRepository repository;

    @Override
    public List<RegistrationEvent> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public RegistrationEvent getEventById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EventNotFoundException());
    }

    @Override
    public RegistrationEvent createEvent(RegistrationEvent event) {
        return repository.save(event);
    }

    @Override
    public RegistrationEvent updateEvent(RegistrationEvent event, Integer id) {
       RegistrationEvent registrationEvent = getEventById(id);
       return  repository.save(registrationEvent);
    }

    @Override
    public void deleteEvent(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public RegistrationEvent getLatestEvent() {
        return repository.findAll().stream().
                sorted(Comparator.nullsLast(
                        (e1, e2) -> e2.getStartEndDate().getStartdate().compareTo(e1.getStartEndDate().getStartdate())
                )).findFirst().orElseThrow(() -> new EventNotFoundException());
    }


}
