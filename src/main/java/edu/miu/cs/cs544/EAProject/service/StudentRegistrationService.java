package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto;

import java.util.List;

public interface StudentRegistrationService {

    StudentRegistrationEventGroupDto getRegistrationListDto(Integer id);

}
