package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationEventGroupDto {

    private String studentName;
    private String email;

    private List<RegistrationGroupDto> registrationGroupDtos;
}
