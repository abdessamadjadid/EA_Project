package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseOfferingDto {

    private String courseOfferingCode;
    private Integer courseOfferingId;
    private Integer capacity;
    private String facultyInitials;
    private Integer academicBlockId;
    private Integer courseId;
    private Integer facultyId;
    private String courseName;
    private String courseCode;
    private String facultyName;

}
