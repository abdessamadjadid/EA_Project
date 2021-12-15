package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Page<Course> getCourses(Pageable pageable);

    Course getCourseById(Integer id);

    Course saveCourse(Course course);

    Course updateCourse(Course course);

}
