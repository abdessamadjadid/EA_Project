package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;

import java.util.List;

public interface EventService {
    List<RegistrationEvent> getAllEvents();
    RegistrationEvent getEventById(Integer id);
    RegistrationEvent createEvent(RegistrationEvent event);
    RegistrationEvent updateEvent(RegistrationEvent event, Integer id);
    void deleteEvent(Integer id);
    RegistrationEvent getLatestEvent();
//    List<RegistrationRequest> getEventRequests(RegistrationEvent event);
//    List<CourseOffering> getAllCourseOffering(RegistrationEvent event);
}
