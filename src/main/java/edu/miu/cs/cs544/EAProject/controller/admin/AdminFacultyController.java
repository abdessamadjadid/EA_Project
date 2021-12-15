package edu.miu.cs.cs544.EAProject.controller.admin;

import edu.miu.cs.cs544.EAProject.dto.FacultyDto;
import edu.miu.cs.cs544.EAProject.dto.UserDetailsDto;
import edu.miu.cs.cs544.EAProject.service.AccountRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admins/faculties")
@RestController
public class AdminFacultyController {

    private final AccountRegistrationService accountRegistrationService;

    @PostMapping
    public FacultyDto registerFaculty(@Valid @RequestBody FacultyDto faculty) {

        UserDetailsDto userDetails = accountRegistrationService.registerFaculty(faculty.getUserId(),
                faculty.getName(), faculty.getEmail(), faculty.getTitle());
        return extractFacultyDto(userDetails);
    }

    @GetMapping("/{id}")
    public FacultyDto findFacultyById(@PathVariable int id) {

        UserDetailsDto userDetails = accountRegistrationService.findUserById(id);
        return extractFacultyDto(userDetails);
    }

    private FacultyDto extractFacultyDto(UserDetailsDto userDetails) {

        if (userDetails == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Faculty not found");

        return userDetails.getRoles().stream()
                .filter(role -> role instanceof FacultyDto)
                .findFirst()
                .map(role -> (FacultyDto) role)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Faculty not found"));
    }
}
