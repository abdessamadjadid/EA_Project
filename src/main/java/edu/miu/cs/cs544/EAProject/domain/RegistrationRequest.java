package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "RegistrationRequest")
@NoArgsConstructor
public class RegistrationRequest {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Embedded
    private Audit audit;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "studentId")
    //private Student student;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Collection<RegistrationEvent> registrationEvent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseOffering_id")
    private Collection<CourseOffering> courseOffering;

    /*public RegistrationRequest(int priority, Student student, RegistrationEvent registrationevent, CourseOffering courseoffering)
    {
        this.priority = priority;
        this.student = student;
        this.registrationevent = registrationevent;
        this.courseoffering = courseoffering;
    }*/

}
