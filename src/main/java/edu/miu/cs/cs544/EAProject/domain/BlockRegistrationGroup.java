package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "BlockRegistrationGroup")
@NoArgsConstructor
public class BlockRegistrationGroup {

    @Id
    @GeneratedValue
    private int id;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    //@ManyToMany(cascade = CascadeType.ALL, mappedBy = "")
    @JoinColumn(name = "blockregistrationgroupId")
    private List<RegistrationGroup> registrationGroup;

    public BlockRegistrationGroup(RegistrationGroup registrationGroup) {
        this.registrationGroup = (List<RegistrationGroup>) registrationGroup;
    }


}
