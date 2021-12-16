package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StudentRegistrationDto {
    private String blockName;
    private String courseCode;
    private String courseName;
    private String facultyName;
    private LocalDateTime startDate;
}
