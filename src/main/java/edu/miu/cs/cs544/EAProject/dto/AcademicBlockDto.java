package edu.miu.cs.cs544.EAProject.dto;

import edu.miu.cs.cs544.EAProject.domain.Semester;
import java.time.LocalDateTime;
import java.util.List;

public class AcademicBlockDto {

    private int id;

    private String code;

    private String name;

    private Semester semester;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<CourseOfferingDto> courseOfferingDtos;
}
