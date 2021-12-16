package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.StringJoiner;


@Entity
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "academicBlockId")
    private AcademicBlock academicBlock;

    @OneToMany(mappedBy = "courseOffering", cascade = CascadeType.ALL)
    private Collection<RegistrationRequest> registrationRequests = new ArrayList<>();

    @OneToMany(mappedBy = "courseOffering")
    private Collection<Registration> registrations = new ArrayList<>();

    @Embedded
    private Audit audit;

    public CourseOffering(int capacity, Faculty faculty, Course course, AcademicBlock academicBlock, Collection<RegistrationRequest> registrationRequests) {
        this.capacity = capacity;
        this.faculty = faculty;
        this.course = course;
        this.academicBlock = academicBlock;
        this.facultyInitials = faculty.getInitials();
        this.code = String.format("%s-%s-%s", course.getCode(), academicBlock.getCode(), faculty.getInitials());

        Optional.ofNullable(registrationRequests)
                .ifPresent(requests -> requests.forEach(this::addRegistrationRequest));
    }

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
    }

    public String getCode() {
        StringJoiner joiner = new StringJoiner("-");
        if(course != null && academicBlock != null && facultyInitials != null){
            joiner.add(course.getCode()).add(academicBlock.getCode()).add(facultyInitials);
        }
        return joiner.toString();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAvailableSeats() {
        return capacity - registrations.size();
    }

    public void addRegistrationRequest(RegistrationRequest request) {
        this.registrationRequests.add(request);
        request.setCourseOffering(this);
    }

    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }
}
