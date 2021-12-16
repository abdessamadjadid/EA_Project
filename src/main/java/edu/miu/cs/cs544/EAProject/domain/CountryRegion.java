package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class CountryRegion implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Audit audit;

    public CountryRegion(String name) {
        this.name = name;
    }
}
