package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationGroupDto;

public interface RegistrationService {

    StudentRegistrationGroupDto updateStudentRegistrationGroup(Integer studentId, Integer groupId);

}
