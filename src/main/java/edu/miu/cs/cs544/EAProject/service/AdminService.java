package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.CourseOffering;
import edu.miu.cs.cs544.EAProject.domain.Student;

public interface AdminService {

    void processLatestEventRegistration();

    void processRegistration(int eventId);

    void assignStudentCourse(Student student, CourseOffering courseOffering);
}
