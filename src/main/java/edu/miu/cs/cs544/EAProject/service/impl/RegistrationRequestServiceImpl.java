package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.RegistrationRequest;
import edu.miu.cs.cs544.EAProject.repository.RegistrationRequestRepository;
import edu.miu.cs.cs544.EAProject.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository repository;

    @Override
    public List<RegistrationRequest> saveRegistrationRequest(List<RegistrationRequest> registrationRequests) {
        return repository.saveAll(registrationRequests);
    }

    @Override
    public RegistrationRequest getRegistrationRequestsByStudentIdEventIdOfferingId(Integer studentId, Integer eventId, Integer offeringId) {
        return repository.getRegistrationRequestByStudentIdEventIdOfferId(studentId, eventId, offeringId);
    }
}
