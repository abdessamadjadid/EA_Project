package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationDto;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto;

import java.util.List;

public interface StudentRegistrationService {

    List<StudentRegistrationDto> getRegistrationListByStudentId(Integer id);

    StudentRegistrationEventGroupDto getRegistrationListDto(Integer id);

}
