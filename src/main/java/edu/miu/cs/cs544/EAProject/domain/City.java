package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@EntityListeners(AuditListener.class)
@NoArgsConstructor
@Getter
@Setter
@Entity
public class City implements Auditable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Audit audit;

    @ManyToOne
    @JoinColumn
    private StateProvince stateprovince;

    public City(String name, StateProvince stateprovince) {
        this.name = name;
        this.stateprovince = stateprovince;
    }
}
