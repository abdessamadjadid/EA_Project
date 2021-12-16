package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.service.AdminService;
import edu.miu.cs.cs544.EAProject.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("registration-events")
@Tag(name = "Registration Event", description="The event API")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    AdminService adminService;

    @GetMapping
    public Page<RegistrationEvent> all(Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }

    @PostMapping
    public RegistrationEvent create(@RequestBody RegistrationEvent event) {
        return eventService.createEvent(event);
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

    @GetMapping("/{id}")
    public void processRegistration(@PathVariable(name = "id") Integer id, @RequestParam String processed) {
        if(processed.equalsIgnoreCase("true"))
                adminService.processRegistration(id);
    }

    @GetMapping("/latest")
    public RegistrationEvent latest() {
        return eventService.getLatestEvent();
    }
}
