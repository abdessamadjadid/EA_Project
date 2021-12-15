package edu.miu.cs.cs544.EAProject.dto;

import lombok.Data;

@Data
public class CourseOfferingDto {

    private String courseOfferingCode;
    private Integer capacity;
    private String facultyInitials;
    private Integer academicBlockId;
    private Integer courseId;
    private Integer facultyId;
    private String courseName;
    private String courseCode;
    private String facultyName;

}
