package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationGroupDto {

    private int id;

    private String name;

    private Collection<AcademicBlockDto> academicBlockDtos;

    private RegistrationEventDto registrationEventDto;

}
