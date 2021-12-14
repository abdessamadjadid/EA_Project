package edu.miu.cs.cs544.EAProject.domain;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private CreatedModifiedDate createdModifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registrationeventId")
    private List<RegistrationEvent> registrationevent;

    //@ManyToMany(cascade = CascadeType.ALL, Mappedby="")
    @JoinColumn(name = "blockRegistrationgroupid")
    private List<BlockRegistrationGroup> blockRegistrationGroups;



    public RegistrationGroup(String name , RegistrationEvent registrationevent, BlockRegistrationGroup blockRegistrationGroup) {
        this.name = name;
        this.registrationevent = (List<RegistrationEvent>) registrationevent;
        //this.blockRegistrationGroup = (List<BlockRegistrationGroup>) blockRegistrationGroup;
    }


}
