package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class RegistrationGroup implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "registrationGroups")
    private Collection<AcademicBlock> academicBlocks;

    @ManyToMany(mappedBy = "registrationGroups")
    private Collection<Student> students;

    @ManyToOne
    @JoinColumn(name = "registrationEventId")
    private RegistrationEvent registrationEvent;

    @Embedded
    private Audit audit;

    public RegistrationGroup(String name, Collection<AcademicBlock> academicBlocks, Collection<Student> students, RegistrationEvent registrationEvent) {
        this.name = name;
        this.academicBlocks = academicBlocks;
        this.students = students;
        this.registrationEvent = registrationEvent;
    }
}
