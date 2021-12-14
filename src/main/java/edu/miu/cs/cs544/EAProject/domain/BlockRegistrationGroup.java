package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BlockRegistrationGroup")
@NoArgsConstructor
public class BlockRegistrationGroup {

    @Id
    @GeneratedValue
    private int id;

    @Embedded
<<<<<<< HEAD
    private Audit createdModifiedDate;
=======
    private Audit audit;

    //@ManyToMany(cascade = CascadeType.ALL, mappedBy = "")
   /* @JoinColumn(name = "blockregistrationgroupId")
    private List<RegistrationGroup> registrationGroup;*/

    /*public BlockRegistrationGroup(RegistrationGroup registrationGroup) {
        this.registrationGroup = (List<RegistrationGroup>) registrationGroup;
    }*/


>>>>>>> main
}
