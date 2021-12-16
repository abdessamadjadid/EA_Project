package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto;
import edu.miu.cs.cs544.EAProject.repository.StudentRegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    @Autowired
    private StudentRegistrationRepository repository;

    @Override
    public StudentRegistrationEventGroupDto getRegistrationListDto(Integer id) {
        return repository.getStudentRegistrationEventGroupDto(id);
    }
}
