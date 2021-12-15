package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.Course;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.repository.CourseRepository;
import edu.miu.cs.cs544.EAProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    // Validation need to add

    @Override
    public Page<Course> getCourses(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Course getCourseById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Course saveCourse(Course course) {
        Audit audit = new Audit();
        audit.setCreatedDate(LocalDateTime.now());
        audit.setModifiedDate(LocalDateTime.now());
        course.setAudit(audit);
        List<Course> courses = repository.findByCode(course.getCode());
        boolean isFound = false;
        if (courses.isEmpty()) {
            return repository.save(course);
        } else {
            for (Course data : courses) {
                if (data.getCode().equals(course.getCode())) isFound = true;
            }
            if (!isFound) return repository.save(course);
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already Created");
        }
    }

    @Override
    public Course updateCourse(Course course) {
        return repository.save(course);
    }
}
