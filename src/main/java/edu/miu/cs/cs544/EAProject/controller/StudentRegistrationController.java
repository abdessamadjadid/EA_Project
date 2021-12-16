package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.Course;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto;
import edu.miu.cs.cs544.EAProject.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityListeners;
import javax.validation.Valid;

@RestController
@RequestMapping("/student/registrations")
@EntityListeners(AuditListener.class)
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService service;

    @GetMapping("/{id}")
    public StudentRegistrationEventGroupDto findById(@Valid @PathVariable(name = "id") Integer id) {
        return service.getRegistrationListDto(id);
    }

}
