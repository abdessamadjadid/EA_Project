package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<RegistrationEvent, Integer> {
    
}
