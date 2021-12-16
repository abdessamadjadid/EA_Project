package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.RegistrationRequest;

import java.util.List;

public interface RegistrationRequestService {

    List<RegistrationRequest> saveRegistrationRequest(List<RegistrationRequest> registrationRequests);

    RegistrationRequest getRegistrationRequestsByStudentIdEventIdOfferingId(Integer studentId, Integer eventId, Integer offeringId);

}
