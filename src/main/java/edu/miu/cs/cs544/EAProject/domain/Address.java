package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Address")
@NoArgsConstructor
@Getter
public class Address {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    private City city;

    public Address(String street, String postalCode ,City city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

}
