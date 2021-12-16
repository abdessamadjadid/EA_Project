package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.*;

import edu.miu.cs.cs544.EAProject.error.ClientException;
import edu.miu.cs.cs544.EAProject.i18n.DefaultMessageSource;
import edu.miu.cs.cs544.EAProject.repository.EventRepository;
import edu.miu.cs.cs544.EAProject.repository.RegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.AdminService;
import edu.miu.cs.cs544.EAProject.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    private final EventService eventService;

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final static MessageSourceAccessor messages = DefaultMessageSource.getAccessor();

    @Override
    public void processLatestEventRegistration() {

        RegistrationEvent event = eventService.getLatestEvent();
        processRegistration(event.getId());
    }

    @Override
    public void processRegistration(int eventId) {

        RegistrationEvent event = eventService.getEventById(eventId);
        validateRegistrationEvent(event);

        // All blocks of an event (entry)
        List<AcademicBlock> blocks = eventService.getEventAcademicBlock(event);

        // All students of an event (entry)
        List<Student> eventStudents = blocks.stream()
                .flatMap(block -> block.getRegistrationGroups().stream())
                .flatMap(group -> group.getStudents().stream())
                .collect(Collectors.toList());

        AtomicBoolean isAscending = new AtomicBoolean(true);

        for (AcademicBlock block : blocks) {

            // Order students by their id asc/des based on the academic block
            LinkedList<Student> students = eventStudents.stream()
                    .distinct()
                    .sorted((s1, s2) -> isAscending.get() ? s1.getId() - s2.getId() : s2.getId() - s1.getId())
                    .collect(Collectors.toCollection(LinkedList::new));

            // Switch order from asc-desc or vice-versa
            isAscending.set(!isAscending.get());

            while (!students.isEmpty()) {

                Student student = students.removeFirst();

                // Based on priority and no of available set, assign course of this block to this student
                assignCourseOffering(block, student);
            }

        }

        event.setProcessed(true);
        eventRepository.save(event);
    }


    private void assignCourseOffering(AcademicBlock block, Student student) {

        // Order requests of a students by their priority
        LinkedList<RegistrationRequest> requests = student.getRegistrationRequests().stream()
                .filter(request -> request.getCourseOffering().getAcademicBlock().getId() == block.getId())
                .sorted(Comparator.comparingInt(RegistrationRequest::getPriority))
                .collect(Collectors.toCollection(LinkedList::new));

        // While a student is not assigned to a course in a block and while seats are available, assign course offering to a student
        while (!requests.isEmpty()) {

            RegistrationRequest request = requests.removeFirst();
            if (request.getCourseOffering().getAvailableSeats() > 0) {
                registerStudentCourse(request.getStudent(), request.getCourseOffering());
                break;
            }
        }
    }

    private void validateRegistrationEvent(RegistrationEvent event) {

        if (event.isProcessed())
            throw new ClientException(messages.getMessage("error.event.processed"));

        if (event.getStartEndDate().getModifiedDate().isAfter(LocalDateTime.now()))
            throw new ClientException(messages.getMessage("error.event.open"));
    }

    @Override
    public void registerStudentCourse(Student student, CourseOffering courseOffering) {
        registrationRepository.save(new Registration(student, courseOffering));
    }
}
