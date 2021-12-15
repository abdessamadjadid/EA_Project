package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "Select * from Course Where code = :code", nativeQuery = true)
    List<Course> findByCode(String code);

}
