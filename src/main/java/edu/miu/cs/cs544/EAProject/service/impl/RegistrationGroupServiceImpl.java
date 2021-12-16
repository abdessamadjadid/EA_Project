package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.domain.RegistrationGroup;
import edu.miu.cs.cs544.EAProject.domain.Student;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationGroupDto;
import edu.miu.cs.cs544.EAProject.error.ClientException;
import edu.miu.cs.cs544.EAProject.repository.RegistrationGroupRepository;
import edu.miu.cs.cs544.EAProject.repository.StudentRegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.EventService;
import edu.miu.cs.cs544.EAProject.service.RegistrationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationGroupServiceImpl implements RegistrationGroupService {

    @Autowired
    private RegistrationGroupRepository repository;
    @Autowired
    private EventService eventService;
    @Autowired
    private StudentRegistrationRepository studentRegistrationRepository;

    @Override
    public RegistrationGroup createGroup(String name, Integer eventId) {
        RegistrationEvent event = eventService.getEventById(eventId);
        return repository.save(new RegistrationGroup(name, event));
    }

    @Override
    public StudentRegistrationGroupDto updateRegistrationGroup(Integer studentId, Integer groupId) {
        Student student = studentRegistrationRepository.getById(studentId);
        RegistrationGroup group = repository.getById(groupId);
        if (student.getName() == null) throw new ClientException("Student doesn't exist");
        if (group.getName() == null) throw new ClientException("Group doesn't exist");
        else {
            group.setStudents(List.of(student));
        }
        group = repository.save(group);
        StudentRegistrationGroupDto dto =
                new StudentRegistrationGroupDto(group.getId(), group.getName(), student.getId(), student.getName());
        return dto;
    }
}
