package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Page<RegistrationEvent> getAllEvents(Pageable pageable);

    RegistrationEvent getEventById(Integer id);

    RegistrationEvent createEvent(RegistrationEvent event);

    RegistrationEvent updateEvent(RegistrationEvent event);

    void deleteEvent(Integer id);

    RegistrationEvent getLatestEvent();

    List<AcademicBlock> getEventAcademicBlock(RegistrationEvent event);

}
