package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AcademicBlockRepository extends JpaRepository<AcademicBlock, Integer> {

    @Query("Select a from AcademicBlock a where a.timespan.createdDate >= :startDate")
    List<AcademicBlock> getByStartDate(LocalDateTime startDate);

    @Query("Select a from AcademicBlock a where a.timespan.createdDate >= :startDate and a.semester = :semester")
    List<AcademicBlock> getByStartDateSemester(LocalDateTime startDate, Semester semester);
}
