package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationGroupDto {
    private Integer groupId;
    private String groupName;
    private Integer studentId;
    private String studentName;
}
