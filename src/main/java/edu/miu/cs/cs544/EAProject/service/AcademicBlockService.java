package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.Semester;

import java.time.LocalDateTime;
import java.util.List;

public interface AcademicBlockService {

    List<AcademicBlock> getAcademicBlockByStartDate(LocalDateTime startDate);

    List<AcademicBlock> getAcademicBlockByStartDateSemester(LocalDateTime startDate, Semester semester);

    AcademicBlock getAcademicBlockById(Integer id);

    AcademicBlock saveAcademicBlock(AcademicBlock block);

    AcademicBlock updateAcademicBlock(AcademicBlock block);

    List<AcademicBlock> getAllAcademicBlock();

    List<CourseOffering> getCourseOfferingMoreCapacity(AcademicBlock block);

}
