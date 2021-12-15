package edu.miu.cs.cs544.EAProject.domain;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "RegistrationGroup")
@NoArgsConstructor

public class RegistrationGroup
{

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    @Embedded
    private Audit audit;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Collection<RegistrationEvent> registrationEvent;

    /*
    //@ManyToMany(cascade = CascadeType.ALL, Mappedby="")
    @JoinColumn(name = "blockRegistrationgroupid")
    private List<BlockRegistrationGroup> blockRegistrationGroups;*/



    public RegistrationGroup(String name , RegistrationEvent registrationevent, BlockRegistrationGroup blockRegistrationGroup) {
        this.name = name;
        //this.registrationevent = (List<RegistrationEvent>) registrationevent;
        //this.blockRegistrationGroup = (List<BlockRegistrationGroup>) blockRegistrationGroup;
    }


}
