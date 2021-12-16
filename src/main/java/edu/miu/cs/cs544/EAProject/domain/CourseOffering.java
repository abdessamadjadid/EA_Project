package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class CourseOffering implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String facultyInitials;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "academicBlockId")
    private AcademicBlock academicBlock;

    @OneToMany(mappedBy = "courseOffering")
    private Collection<RegistrationRequest> registrationRequests;

    @Embedded
    private Audit audit;

    public CourseOffering(String code, String facultyInitials, int capacity, Faculty faculty,
                          Course course, AcademicBlock academicBlock, Collection<RegistrationRequest> registrationRequests) {
        this.code = code;
        this.facultyInitials = facultyInitials;
        this.capacity = capacity;
        this.faculty = faculty;
        this.course = course;
        this.academicBlock = academicBlock;
        this.registrationRequests = registrationRequests;
    }

    public CourseOffering(String facultyInitials, int capacity, Faculty faculty, Course course, AcademicBlock academicBlock) {
        this.facultyInitials = facultyInitials;
        this.capacity = capacity;
        this.faculty = faculty;
        this.course = course;
        this.academicBlock = academicBlock;
        this.code = generateCode();
    }

    private String generateCode() {
        StringJoiner stringJoiner = new StringJoiner("-");
        stringJoiner.add(course.getCode()).add(academicBlock.getCode()).add(facultyInitials);
        return stringJoiner.toString();
    }

    public int getAvailableSeats() {
        return 0;
    }
}
