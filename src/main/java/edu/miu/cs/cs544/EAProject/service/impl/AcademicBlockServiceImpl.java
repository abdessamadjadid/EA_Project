package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AcademicBlockServiceImpl implements AcademicBlockService {

    @Autowired
    private AcademicBlockService academicBlockService;

    @Override
    public AcademicBlock getAcademicBlockByStartDate(LocalDateTime startDate) {
        return null;
    }

    @Override
    public AcademicBlock getAcademicBlockById(Integer id) {
        return null;
    }

    @Override
    public AcademicBlock saveAcademicBlock(AcademicBlock course) {
        return null;
    }

    @Override
    public AcademicBlock updateCourse(AcademicBlock course) {
        return null;
    }
}
