package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.Course;
import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.Faculty;
import edu.miu.cs.cs544.EAProject.dto.CourseOfferingResponseDto;
import edu.miu.cs.cs544.EAProject.error.ClientException;
import edu.miu.cs.cs544.EAProject.repository.CourseOfferingRepository;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import edu.miu.cs.cs544.EAProject.service.CourseOfferingService;
import edu.miu.cs.cs544.EAProject.service.CourseService;
import edu.miu.cs.cs544.EAProject.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    private CourseOfferingRepository repository;

    @Autowired
    private AcademicBlockService blockService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FacultyService facultyService;

    @Override
    public CourseOffering getCourseOfferingById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public List<CourseOffering> getCourseOfferingByFacultyIdCourseIdBlockId(Integer facultyId, Integer courseId, Integer blockId) {
        return repository.findByFacultyIdCourseIdBlockId(facultyId, courseId, blockId);
    }

    @Override
    public CourseOfferingResponseDto saveCourseOffering(Integer capacity, String facultyInitials, Integer academicBlockId,
                                                        Integer courseId, Integer facultyId) {
        try {
            AcademicBlock block = blockService.getAcademicBlockById(academicBlockId);
            Faculty faculty = facultyService.getFaultyById(facultyId);
            Course course = courseService.getCourseById(courseId);
            if (block.getCode() == null || faculty.getRoleName() == null || course.getCode() == null)
                throw new ClientException("error.generic.message");
            CourseOffering offering = new CourseOffering(facultyInitials, capacity, faculty, course, block);
            return convertOfferingResponseDto(repository.save(offering));
        } catch (Exception e) {
            throw new ClientException("error.generic.message");
        }

    }

    @Override
    public void deleteCourseOffering(Integer id) {
        try {
            CourseOffering courseOffering = getCourseOfferingById(id);
            if (courseOffering == null) {
                throw new ClientException("error.courseOfferingId.noRecord");
            } else {
                repository.delete(courseOffering);
            }
        } catch (EntityNotFoundException e) {
            throw new ClientException("error.courseOfferingId.noRecord");
        }
    }

    private CourseOfferingResponseDto convertOfferingResponseDto(CourseOffering offering) {
        return new CourseOfferingResponseDto(offering.getId(), offering.getCode(),
                offering.getFacultyInitials(), offering.getCapacity(), offering.getFaculty().getName(),
                offering.getFaculty().getEmail(), offering.getFaculty().getTitle());
    }
}
