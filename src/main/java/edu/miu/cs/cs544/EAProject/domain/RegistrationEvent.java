package edu.miu.cs.cs544.EAProject.domain;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "RegistrationEvent")
@NoArgsConstructor
public class RegistrationEvent
{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    @Embedded
    private StartEndDate startEndDate;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name="group_id")
   private Collection<RegistrationGroup> registrationGroups;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name="request_id")
   private Collection<RegistrationRequest> registrationRequests;


}
