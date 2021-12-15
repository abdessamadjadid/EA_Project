package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.error.ClientException;
import edu.miu.cs.cs544.EAProject.repository.CourseOfferingRepository;
import edu.miu.cs.cs544.EAProject.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    private CourseOfferingRepository repository;

    @Override
    public CourseOffering getCourseOfferingById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public List<CourseOffering> getCourseOfferingByFacultyIdCourseIdBlockId(Integer facultyId, Integer courseId, Integer blockId) {
        return repository.findByFacultyIdCourseIdBlockId(facultyId, courseId, blockId);
    }

    @Override
    public CourseOffering saveCourseOffering(CourseOffering courseOffering) {
        return repository.save(courseOffering);
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
}
