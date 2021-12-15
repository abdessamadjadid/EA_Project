package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;

import java.time.LocalDateTime;

public interface AcademicBlockService {

    AcademicBlock getAcademicBlockByStartDate(LocalDateTime startDate);

    AcademicBlock getAcademicBlockById(Integer id);

    AcademicBlock saveAcademicBlock(AcademicBlock course);

    AcademicBlock updateCourse(AcademicBlock course);

}
