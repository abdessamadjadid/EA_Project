package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.Student;

public interface AdminService {

    /**
     * Finds the latest un-processed registration event and initiate registration process
     */
    void processLatestEventRegistration();

    /**
     * Finds event by id and initiate registration process
     */
    void processRegistration(int eventId);

    /**
     * Registers a student for a course
     */
    void registerStudentCourse(Student student, CourseOffering courseOffering);
}
