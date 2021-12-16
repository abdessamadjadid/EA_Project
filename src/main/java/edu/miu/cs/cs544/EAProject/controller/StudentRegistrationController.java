package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.dto.RegistrationRequestDto;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationDto;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto;
import edu.miu.cs.cs544.EAProject.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService service;

    @GetMapping("/registrations")
    public List<StudentRegistrationDto> getRegistrationListByStudentId(@Valid @RequestParam(name = "id") Integer id) {
        return service.getRegistrationListByStudentId(id);
    }

    @GetMapping("/registrations-event")
    public StudentRegistrationEventGroupDto getStudentRegistrationListByEvent(@Valid @RequestParam(name = "id") Integer id) {
        return service.getRegistrationListDto(id);
    }

    @PostMapping("/registration-requests")
    public ResponseEntity<String> saveRegistrationRequest(@Valid @RequestBody List<RegistrationRequestDto> requestDtos) {
        return service.saveRegistrationRequest(requestDtos);
    }
}
