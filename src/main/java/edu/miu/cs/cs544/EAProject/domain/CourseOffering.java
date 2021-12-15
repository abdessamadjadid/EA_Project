package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
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

    public int getAvailableSeats() {
        return 0;
    }
}
