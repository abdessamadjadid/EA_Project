package edu.miu.cs.cs544.EAProject.dto;

import lombok.Data;

@Data
public class CourseOfferingDto {

    private Integer capacity;
    private String facultyInitials;
    private Integer academicBlockId;
    private Integer courseId;
    private Integer facultyId;

}
