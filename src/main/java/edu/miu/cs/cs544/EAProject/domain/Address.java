package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Address")
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    @Embedded
    private Audit audit;


    public Address(String street, String postalCode) {
        this.street = street;
        this.postalCode = postalCode;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    private City city;

    public Address(String street, String postalCode ,City city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }
}
