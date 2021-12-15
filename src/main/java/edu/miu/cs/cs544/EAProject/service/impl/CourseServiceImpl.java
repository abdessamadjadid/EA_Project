package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.Course;
import edu.miu.cs.cs544.EAProject.repository.CourseRepository;
import edu.miu.cs.cs544.EAProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return repository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return repository.save(course);
    }
}
