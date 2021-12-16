package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {

    @Query(value = "from RegistrationRequest r where r.student.id = :studentId and r.event.id = :eventId and r.courseOffering.id = :offeringId")
    RegistrationRequest getRegistrationRequestByStudentIdEventIdOfferId(Integer studentId, Integer eventId, Integer offeringId);

}
