package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.dto.RegistrationEventDto;
import edu.miu.cs.cs544.EAProject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration-events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public Page<RegistrationEvent> all(Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }

//    @GetMapping("/{id}")
//    public RegistrationEvent one(@PathVariable(name = "id") Integer id) {
//        return eventService.getEventById(id);
//    }

    @PostMapping
    public RegistrationEvent create(@RequestBody RegistrationEventDto event) {
        return eventService.createEvent(event.getName(), event.getStartDate().atStartOfDay(), event.getEndDate().atStartOfDay());
    }

    @PutMapping
    public RegistrationEvent update(@RequestBody RegistrationEvent event) {
        return eventService.updateEvent(event);
    }

    @PatchMapping
    public RegistrationEvent modified(@RequestBody RegistrationEvent event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
         eventService.deleteEvent(id);
    }

    @GetMapping("/{latest}")
    public RegistrationEvent latest() {
        return eventService.getLatestEvent();
    }
}
