package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.RegistrationGroup;
import edu.miu.cs.cs544.EAProject.repository.RegistrationGroupRepository;
import edu.miu.cs.cs544.EAProject.service.RegistrationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationGroupServiceImpl implements RegistrationGroupService {

    @Autowired
    private RegistrationGroupRepository repository;

    @Override
    public RegistrationGroup createGroup(RegistrationGroup group) {
        return repository.save(group);
    }
}
