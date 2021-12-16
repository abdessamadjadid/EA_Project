package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
      if(isEventOpen()){
          this.status = EventStatus.OPEN;
      }
      return this.status;
    }

   public Boolean isEventOpen(){
       return ChronoUnit.NANOS.between(LocalDateTime.now(), this.startEndDate.getModifiedDate()) > 0 ? true: false;
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

    public RegistrationEvent(String name, Audit startEndDate) {
        this.name = name;
        this.startEndDate = startEndDate;
    }
}
