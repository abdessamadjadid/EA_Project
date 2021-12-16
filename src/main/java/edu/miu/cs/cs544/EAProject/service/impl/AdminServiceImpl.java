package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.repository.StudentRegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.AdminService;
import edu.miu.cs.cs544.EAProject.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    private final EventService eventService;
    private final StudentRegistrationRepository studentRepository;


    @Override
    public void processLatestEventRegistration() {

        RegistrationEvent event = eventService.getLatestEvent();
        processRegistration(event.getId());
    }

    @Override
    public void processRegistration(int eventId) {

        RegistrationEvent event = eventService.getEventById(eventId);

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
                    .sorted((s1, s2) -> isAscending.get() ? s1.getId() - s2.getId() : s2.getId() - s1.getId())
                    .collect(Collectors.toCollection(LinkedList::new));

            // Switch order from asc-desc or vice-versa
            isAscending.set(!isAscending.get());

            while (!students.isEmpty()) {

                Student student = students.removeFirst();

                // Order requests of a students by their priority
                LinkedList<RegistrationRequest> requests = student.getRegistrationRequests().stream()
                        .filter(request -> request.getCourseOffering().getAcademicBlock().getId() == block.getId())
                        .sorted(Comparator.comparingInt(RegistrationRequest::getPriority))
                        .collect(Collectors.toCollection(LinkedList::new));

                // While a student is not assigned to a course in a block and while seats are available, assign course offering to a student
                while (!requests.isEmpty()) {

                    RegistrationRequest request = requests.removeFirst();
                    if (request.getCourseOffering().getAvailableSeats() > 0) {
                        assignStudentCourse(request.getStudent(), request.getCourseOffering());
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void assignStudentCourse(Student student, CourseOffering courseOffering) {

        student.addCourse(courseOffering);
        studentRepository.flush();
    }
}
