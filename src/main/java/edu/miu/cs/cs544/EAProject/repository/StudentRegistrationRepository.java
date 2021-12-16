package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.Student;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationRepository extends JpaRepository<Student, Integer> {

    @Query("Select new edu.miu.cs.cs544.EAProject.dto.StudentRegistrationEventGroupDto")
    StudentRegistrationEventGroupDto getStudentRegistrationEventGroupDto(Integer id);

}
