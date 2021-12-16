package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseOfferingResponseDto {

    private Integer id;
    private String code;
    private String facultyInitials;
    private Integer capacity;
    private String facutlyName;
    private String facutlyEmail;
    private String facutlyTitle;

}
