package edu.miu.cs.cs544.EAProject.dto;

import edu.miu.cs.cs544.EAProject.domain.Address;

import java.util.List;

public class StudentRegistrationEventGroupDto {

    private String studentName;
    private String email;
    private Address mailAddress;
    private Address homeAddress;

    private List<RegistrationGroupDto> registrationGroupDtos;
}
