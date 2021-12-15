package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.Faculty;
import edu.miu.cs.cs544.EAProject.repository.FacultyRepository;
import edu.miu.cs.cs544.EAProject.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository repository;

    @Override
    public Faculty getFaultyById(Integer id) {
        return repository.getById(id);
    }
}
