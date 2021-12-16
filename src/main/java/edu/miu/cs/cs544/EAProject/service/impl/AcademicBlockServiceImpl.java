package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.Semester;
import edu.miu.cs.cs544.EAProject.repository.AcademicBlockRepository;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import edu.miu.cs.cs544.EAProject.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AcademicBlockServiceImpl implements AcademicBlockService {

    @Autowired
    private AcademicBlockRepository repository;

    @Override
    public List<AcademicBlock> getAcademicBlockByStartDate(LocalDateTime startDate) {
        return repository.getByStartDate(startDate);
    }

    @Override
    public List<AcademicBlock> getAcademicBlockByStartDateSemester(LocalDateTime startDate, Semester semester) {
        return repository.getByStartDateSemester(startDate, semester);
    }

    @Override
    public AcademicBlock getAcademicBlockById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public AcademicBlock saveAcademicBlock(AcademicBlock block) {
        return repository.save(block);
    }

    @Override
    public AcademicBlock updateAcademicBlock(AcademicBlock block) {
        return repository.save(block);
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
