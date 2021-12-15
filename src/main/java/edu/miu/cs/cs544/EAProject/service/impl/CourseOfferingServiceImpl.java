package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.repository.CourseOfferingRepository;
import edu.miu.cs.cs544.EAProject.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is course offering of" + id);
            } else {
                repository.delete(courseOffering);
                throw new ResponseStatusException(HttpStatus.OK, "CourseOffering is successfully deleted.");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is course offering of " + id);
        }
    }
}
