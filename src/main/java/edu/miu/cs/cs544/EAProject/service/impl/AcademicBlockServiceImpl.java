package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.repository.AcademicBlockRepository;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import edu.miu.cs.cs544.EAProject.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AcademicBlockServiceImpl implements AcademicBlockService {

    @Autowired
    private AcademicBlockRepository repository;

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

    @Override
    public List<AcademicBlock> getAllAcademicBlock() {
        return repository.findAll();
    }

    @Override
    public List<CourseOffering> getCourseOfferingMoreCapacity(AcademicBlock block) {
        return FunctionUtil.getCourseOfferingMoreCapacity.apply(block);
    }
}
