package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@NoArgsConstructor
@Data
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
}
