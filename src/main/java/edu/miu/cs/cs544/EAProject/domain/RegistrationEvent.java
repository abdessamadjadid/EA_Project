package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Setter
@Getter
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registrationEvent")
    private Collection<RegistrationGroup> registrationGroups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Collection<RegistrationRequest> registrationRequests;

    @Transient
    EventStatus status = EventStatus.CLOSED;

    public RegistrationEvent(String name, Audit startEndDate) {
        this.name = name;
        this.startEndDate = startEndDate;
        status = getStatus();
    }

    public void addGroup(RegistrationGroup group) {
        if (group != null) {
            registrationGroups.add(group);
        }
    }

    public void addRequest(RegistrationRequest request) {
        if (request != null) {
            registrationRequests.add(request);
        }
    }

    public EventStatus getStatus() {
        return this.status = switch (isEventOpen()) {
            case 1 -> EventStatus.OPEN;
            default -> EventStatus.CLOSED;
        };
    }

    public int isEventOpen() {
        if (LocalDateTime.now().toLocalDate().compareTo(startEndDate.getModifiedDate().toLocalDate()) > 0 ||
                LocalDateTime.now().toLocalDate().compareTo(startEndDate.getCreatedDate().toLocalDate()) < 0) {
            return 0;
        } else return 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartEndDate(Audit startEndDate) {
        this.startEndDate = startEndDate;
    }

    public void setRegistrationGroups(Collection<RegistrationGroup> registrationGroups) {
        this.registrationGroups = registrationGroups;
    }

    public void setRegistrationRequests(Collection<RegistrationRequest> registrationRequests) {
        this.registrationRequests = registrationRequests;
    }
}
