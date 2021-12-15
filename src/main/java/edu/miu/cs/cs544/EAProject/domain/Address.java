package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EntityListeners(AuditListener.class)
@NoArgsConstructor
@Data
@Entity
public class Address implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn
    private City city;

    @Embedded
    private Audit audit;

    public Address(String street, String postalCode, City city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }
}
