package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.service.AdminService;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.domain.RegistrationRequest;
import edu.miu.cs.cs544.EAProject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Autowired
    EventService eventService;

    @Override
    public void processRegistration() {
        //Get latest Registration Event
       RegistrationEvent event =  eventService.getLatestEvent();
       // Get list of request
       // List<RegistrationRequest> requestList = eventService.getEventRequests(event);
       //Get list Course offering
       // List<CourseOffering> courseOfferings = eventService.getAllCourseOffering(event);
        //Check capacity per priority (recursively)
        // success Register student



    }
}
