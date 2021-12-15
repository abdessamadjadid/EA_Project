package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.Course;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityListeners;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/courses")
@EntityListeners(AuditListener.class)
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping(params = {"page"})
    public Page<Course> findAll(Pageable pageable) {
        return service.getCourses(pageable);
    }

    @GetMapping("/{id}")
    public Course findById(@Valid @PathVariable(name = "id") Integer id) {
        return service.getCourseById(id);
    }

    @PostMapping
    public Course saveCourse(@RequestBody Course course) {
        Audit audit = new Audit();
        audit.setCreatedDate(LocalDateTime.now());
        audit.setModifiedDate(LocalDateTime.now());
        course.setAudit(audit);
        return service.saveCourse(course);
    }

    @PutMapping
    public Course updateCourse(@Valid @RequestBody Course course) {
        return service.updateCourse(course);
    }

}
