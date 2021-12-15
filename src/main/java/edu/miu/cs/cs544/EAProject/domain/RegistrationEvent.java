package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Entity
@Data
@Table(name = "RegistrationEvent")
@NoArgsConstructor
public class RegistrationEvent {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdDate", column = @Column(name = "startDate")),
            @AttributeOverride(name = "modifiedDate", column = @Column(name = "endDate"))
    })
    private Audit startEndDate;

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
       return ChronoUnit.NANOS.between(LocalDateTime.now(), this.startEndDate.getModifiedDate()) == -1 ? 1: 0;
   }




}
