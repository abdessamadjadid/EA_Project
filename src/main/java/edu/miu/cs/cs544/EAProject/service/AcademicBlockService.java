package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AcademicBlockService {

    AcademicBlock getAcademicBlockByStartDate(LocalDateTime startDate);

    AcademicBlock getAcademicBlockById(Integer id);

    AcademicBlock saveAcademicBlock(AcademicBlock course);

    AcademicBlock updateCourse(AcademicBlock course);

    List<AcademicBlock> getAllAcademicBlock();

    List<CourseOffering> getCourseOfferingMoreCapacity(AcademicBlock block);

}
