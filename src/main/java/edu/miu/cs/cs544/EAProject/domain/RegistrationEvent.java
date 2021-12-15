package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class RegistrationEvent implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdDate", column = @Column(name = "startDate")),
            @AttributeOverride(name = "modifiedDate", column = @Column(name = "endDate"))
    })
    private Audit timespan;

    @Embedded
    private Audit audit;

    @OneToMany(mappedBy = "registrationEvent")
    private Collection<RegistrationGroup> registrationGroups;

    public RegistrationEvent(String name, Audit timespan, Collection<RegistrationGroup> registrationGroups) {
        this.name = name;
        this.timespan = timespan;
        this.registrationGroups = registrationGroups;
    }
}
