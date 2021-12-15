package edu.miu.cs.cs544.EAProject.domain;


import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@EntityListeners(AuditListener.class)
@NoArgsConstructor
@Data
@Entity
public class AcademicBlock implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdDate", column = @Column(name = "startDate")),
            @AttributeOverride(name = "modifiedDate", column = @Column(name = "endDate"))
    })
    private Audit timespan;

    @Embedded
    private Audit audit;

    @ManyToMany
    @JoinTable(name = "BlockRegistrationGroup",
            joinColumns = @JoinColumn(name = "academicBlock"),
            inverseJoinColumns = @JoinColumn(name = "registrationGroup"))
    private Collection<RegistrationGroup> registrationGroups;

    @OneToMany(mappedBy = "academicBlock", cascade = CascadeType.ALL)
    private Collection<CourseOffering> courseOfferings;


    public AcademicBlock(String code, String name, Semester semester, Audit timespan,
                         Collection<RegistrationGroup> registrationGroups, Collection<CourseOffering> courseOfferings) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.timespan = timespan;
        this.registrationGroups = registrationGroups;
        this.courseOfferings = courseOfferings;
    }
}
