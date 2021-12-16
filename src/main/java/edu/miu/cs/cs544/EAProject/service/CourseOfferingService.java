package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.dto.CourseOfferingResponseDto;

import java.util.List;

public interface CourseOfferingService {

    CourseOffering getCourseOfferingById(Integer id);

    List<CourseOffering> getCourseOfferingByFacultyIdCourseIdBlockId(Integer facultyId, Integer courseId, Integer blockId);

    CourseOfferingResponseDto saveCourseOffering(Integer capacity, String facultyInitials, Integer academicBlockId, Integer courseId, Integer facultyId);

    void deleteCourseOffering(Integer id);

}
