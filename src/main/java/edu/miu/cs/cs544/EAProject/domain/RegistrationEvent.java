package edu.miu.cs.cs544.EAProject.domain;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "RegistrationEvent")
@NoArgsConstructor
public class RegistrationEvent
{
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    @Embedded
    private StartEndDate startEndDate;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "registrationgroupId")
    private RegistrationGroup registrationgroup;

    public RegistrationEvent(String name , RegistrationGroup registrationgroup) {
        this.name = name;
        this.registrationgroup = registrationgroup;
    }
    */

}
