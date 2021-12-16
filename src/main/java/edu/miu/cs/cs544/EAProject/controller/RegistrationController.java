package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationGroupDto;
import edu.miu.cs.cs544.EAProject.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @PostMapping("/student-group")
    private StudentRegistrationGroupDto create(@RequestParam(name = "studentId") Integer studentId,
                                               @RequestParam(name = "registrationGroupId") Integer registration) {
        return service.updateStudentRegistrationGroup(studentId, registration);
    }

}
