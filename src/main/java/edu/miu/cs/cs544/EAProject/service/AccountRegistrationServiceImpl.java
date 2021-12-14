package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.Admin;
import edu.miu.cs.cs544.EAProject.domain.Faculty;
import edu.miu.cs.cs544.EAProject.domain.Student;
import edu.miu.cs.cs544.EAProject.domain.User;
import edu.miu.cs.cs544.EAProject.dto.*;
import edu.miu.cs.cs544.EAProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetailsDto registerStudent(int userId, String studentId, String name, String email) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown user"));

        boolean isAlreadyStudent = user.getRoles().stream().anyMatch(role -> role instanceof Student);

        if (!isAlreadyStudent) {
            Student student = new Student(studentId, name, email);
            user.addRole(student);
            user = userRepository.save(user);
        }
        return toUserDetailsDto(user);
    }

    @Override
    public UserDetailsDto registerFaculty(int userId, String name, String email, String title) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown user"));

        boolean isAlreadyFaculty = user.getRoles().stream().anyMatch(role -> role instanceof Faculty);

        if (!isAlreadyFaculty) {
            Faculty faculty = new Faculty(name, email, title);
            user.addRole(faculty);
            user = userRepository.save(user);
        }
        return toUserDetailsDto(user);
    }

    @Override
    public UserDetailsDto registerAdmin(int userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown user"));

        boolean isAlreadyAdmin = user.getRoles().stream().anyMatch(role -> role instanceof Admin);

        if (!isAlreadyAdmin) {
            Admin admin = new Admin();
            user.addRole(admin);
            user = userRepository.save(user);
        }

        return toUserDetailsDto(user);
    }

    @Override
    public UserDetailsDto findUserById(int userId) {

        return userRepository.findById(userId)
                .map(this::toUserDetailsDto)
                .orElse(null);
    }

    private UserDetailsDto toUserDetailsDto(User user) {

        List<RoleDto> roles = user.getRoles().stream()
                .map(role -> {

                    if (role instanceof Student) return modelMapper.map(role, StudentDto.class);
                    else if (role instanceof Faculty) return modelMapper.map(role, FacultyDto.class);
                    else if (role instanceof Admin) return modelMapper.map(role, AdminDto.class);
                    throw new RuntimeException("Unknown role");
                })
                .collect(Collectors.toList());

        return new UserDetailsDto(user.getId(), user.getUsername(), roles);
    }
}
