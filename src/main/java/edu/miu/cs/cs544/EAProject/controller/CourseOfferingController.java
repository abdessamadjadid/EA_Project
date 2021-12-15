package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.dto.CourseOfferingDto;
import edu.miu.cs.cs544.EAProject.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-offerings")
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService service;

    @GetMapping
    public CourseOffering findById(@RequestParam(name = "id") Integer id) {
        return service.getCourseOfferingById(id);
    }

    @PostMapping
    public CourseOffering saveCourse(@RequestBody CourseOfferingDto courseOffering) {
        return service.saveCourseOffering(courseOffering.getCapacity(), courseOffering.getFacultyInitials(),
                courseOffering.getAcademicBlockId(), courseOffering.getCourseId(), courseOffering.getFacultyId());
    }

    @DeleteMapping
    public void deleteCourseOffering(@RequestParam Integer id) {
        service.deleteCourseOffering(id);
    }
}
