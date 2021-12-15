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
    private Audit audit;
}
