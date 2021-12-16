package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"registrationGroups", "registrationRequests"})
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class RegistrationEvent implements Auditable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdDate", column = @Column(name = "startDate")),
            @AttributeOverride(name = "modifiedDate", column = @Column(name = "endDate"))
    })
    private Audit startEndDate;

    @Embedded
    private Audit audit;

    @OneToMany(mappedBy = "registrationEvent", cascade = CascadeType.ALL)
    private Collection<RegistrationGroup> registrationGroups = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "registrationRequestId")
    private Collection<RegistrationRequest> registrationRequests = new ArrayList<>();

    private boolean isProcessed = false;

    @Transient
    EventStatus status = EventStatus.CLOSED;

    public void addGroup(RegistrationGroup group) {
        registrationGroups.add(group);
        group.setRegistrationEvent(this);
    }

    public void addRequest(RegistrationRequest request) {
        registrationRequests.add(request);
    }

    public EventStatus getStatus() {
//      return this.status = switch(isEventOpen()){
//           case 1 -> EventStatus.OPEN;
//           default -> EventStatus.CLOSED;
//       };
        return EventStatus.CLOSED;
    }

    public int isEventOpen() {
        return ChronoUnit.NANOS.between(LocalDateTime.now(), this.startEndDate.getModifiedDate()) == -1 ? 1 : 0;
    }

    public RegistrationEvent(String name, Audit startEndDate) {
        this.name = name;
        this.startEndDate = startEndDate;
    }
}
