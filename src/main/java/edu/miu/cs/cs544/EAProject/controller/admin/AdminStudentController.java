package edu.miu.cs.cs544.EAProject.controller.admin;

import edu.miu.cs.cs544.EAProject.dto.StudentDto;
import edu.miu.cs.cs544.EAProject.dto.UserDetailsDto;
import edu.miu.cs.cs544.EAProject.service.AccountRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * Set of endpoints for admins to manage students
 */
@RequiredArgsConstructor
@RequestMapping("/admins/students")
@RestController
public class AdminStudentController {

    private final AccountRegistrationService accountRegistrationService;

    @PostMapping
    public StudentDto registerStudent(@Valid @RequestBody StudentDto student) {
        UserDetailsDto userDetails = accountRegistrationService.registerStudent(student.getUserId(), student.getStudentId(),
                student.getName(), student.getEmail(), student.getMailingAddressId(), student.getHomeAddressId());
        return extractStudentDto(userDetails);
    }

    @GetMapping("/{id}")
    public StudentDto findStudentByUserId(@PathVariable int id) {

        UserDetailsDto userDetails = accountRegistrationService.findUserById(id);
        return extractStudentDto(userDetails);
    }

    private StudentDto extractStudentDto(UserDetailsDto userDetails) {

        if (userDetails == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Faculty not found");

        return userDetails.getRoles().stream()
                .filter(role -> role instanceof StudentDto)
                .findFirst()
                .map(role -> (StudentDto) role)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }
}
