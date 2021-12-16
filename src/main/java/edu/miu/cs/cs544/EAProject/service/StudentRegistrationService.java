package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationDto;

import java.util.List;

public interface StudentRegistrationService {

    List<StudentRegistrationDto> getRegistrationListByStudentId(Integer id);

}
