package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class RegistrationRequest implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseOfferingId", nullable = false)
    private CourseOffering courseOffering;

    @Column(nullable = false)
    private int priority;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private RegistrationEvent event;

    @Embedded
    private Audit audit;
}
