package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class StateProvince implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Embedded
    private Audit audit;

    @ManyToOne
    @JoinColumn
    private CountryRegion countryRegion;

    public StateProvince(String name, String code, CountryRegion countryRegion) {
        this.name = name;
        this.code = code;
        this.countryRegion = countryRegion;
    }
}
