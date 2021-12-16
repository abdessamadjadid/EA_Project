package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.*;
import edu.miu.cs.cs544.EAProject.dto.StudentRegistrationDto;
import edu.miu.cs.cs544.EAProject.repository.StudentRegistrationRepository;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import edu.miu.cs.cs544.EAProject.service.CourseService;
import edu.miu.cs.cs544.EAProject.service.FacultyService;
import edu.miu.cs.cs544.EAProject.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    @Autowired
    private StudentRegistrationRepository repository;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AcademicBlockService blockService;

    @Override
    public List<StudentRegistrationDto> getRegistrationListByStudentId(Integer id) {
        List<StudentRegistrationDto> dtos = new ArrayList<>();
        Student student = repository.getById(id);
        List<CourseOffering> offerings = student.getCourseOfferings().stream().collect(Collectors.toList());
        for (CourseOffering offering : offerings) {
            Faculty faculty = facultyService.getFaultyById(offering.getFaculty().getId());
            Course course = courseService.getCourseById(offering.getCourse().getId());
            AcademicBlock block = blockService.getAcademicBlockById(offering.getAcademicBlock().getId());
            StudentRegistrationDto dto = new StudentRegistrationDto(block.getName(),
                    course.getCode(), course.getName(), faculty.getName(), block.getTimespan().getCreatedDate());
            dtos.add(dto);
        }
        return dtos;
    }
}
