package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Entity
@Data
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

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name="group_id")
   private Collection<RegistrationGroup> registrationGroups;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name="request_id")
   private Collection<RegistrationRequest> registrationRequests;

   @Transient
   EventStatus status = EventStatus.CLOSED;

   public void addGroup(RegistrationGroup group){
       if(group != null){
           registrationGroups.add(group);
       }
   }

   public void addRequest(RegistrationRequest request){
       if(request != null){
           registrationRequests.add(request);
       }
   }

   public EventStatus getStatus(){
      return this.status = switch(isEventOpen()){
           case 1 -> EventStatus.OPEN;
           default -> EventStatus.CLOSED;
       };
   }

   public int isEventOpen(){
       return ChronoUnit.NANOS.between(LocalDateTime.now(), this.startEndDate.getModifiedDate()) > 0 ? 1: 0;
   }
}
