package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.RegistrationGroup;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationGroupDto;

public interface RegistrationGroupService {
    RegistrationGroup createGroup(String name, Integer eventId);

    StudentRegistrationGroupDto updateRegistrationGroup(Integer studentId, Integer groupId);
}
