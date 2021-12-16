package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationGroupDto;
import edu.miu.cs.cs544.EAProject.service.RegistrationGroupService;
import edu.miu.cs.cs544.EAProject.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationGroupService registrationGroupService;

    @Override
    public StudentRegistrationGroupDto updateStudentRegistrationGroup(Integer studentId, Integer groupId) {
        return registrationGroupService.updateRegistrationGroup(studentId, groupId);
    }
}
