package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;

import java.util.List;

public interface CourseOfferingService {

    CourseOffering getCourseOfferingById(Integer id);

    List<CourseOffering> getCourseOfferingByFacultyIdCourseIdBlockId(Integer facultyId, Integer courseId, Integer blockId);

    CourseOffering saveCourseOffering(CourseOffering courseOffering);

    void deleteCourseOffering(Integer id);

}
