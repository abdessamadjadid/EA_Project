package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationDto;
import edu.miu.cs.cs544.EAProject.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("registrations")
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService service;

    @GetMapping
    public List<StudentRegistrationDto> getRegistrationListByStudentId(@Valid @RequestParam(name = "id") Integer id) {
        return service.getRegistrationListByStudentId(id);
    }

}
