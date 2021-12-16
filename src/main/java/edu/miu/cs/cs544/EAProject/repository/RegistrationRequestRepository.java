package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {

    @Query(value = "Select * from RegistrationRequest r where r.studentId = :studentId and r.request_id = :eventId and r.courseOfferingId = :offeringId",
            nativeQuery = true)
    RegistrationRequest getRegistrationRequestByStudentIdEventIdOfferId(Integer studentId, Integer eventId, Integer offeringId);

}
