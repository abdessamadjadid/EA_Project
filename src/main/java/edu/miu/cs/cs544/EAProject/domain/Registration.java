package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditListener.class)
@Entity @Getter @Setter @NoArgsConstructor
public class Registration implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Embedded
    private Audit audit;

    @Column(updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseOfferingId")
    private CourseOffering courseOffering;

    public Registration(Student student, CourseOffering courseOffering) {
        setStudent(student);
        this.courseOffering = courseOffering;
    }

    public void setStudent(Student student) {
        this.student = student;
        student.addRegistration(this);
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
        courseOffering.addRegistration(this);
    }
}
