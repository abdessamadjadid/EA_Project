package edu.miu.cs.cs544.EAProject.dto;

import edu.miu.cs.cs544.EAProject.domain.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicBlockDto {

    private int id;

    private String code;

    private String name;

    private Semester semester;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<CourseOfferingDto> courseOfferingDtos;
}
