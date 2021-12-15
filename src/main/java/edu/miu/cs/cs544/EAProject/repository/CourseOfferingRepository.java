package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Integer> {

    @Query(value = "Select * from CourseOffering Where facultyId = :facultyId and courseId = :courseId and academicBlockId = :blockId",
            nativeQuery = true)
    List<CourseOffering> findByFacultyIdCourseIdBlockId(Integer facultyId, Integer courseId, Integer blockId);

}
