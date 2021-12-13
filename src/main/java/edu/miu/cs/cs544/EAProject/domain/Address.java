package edu.miu.cs.cs544.EAProject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    public Address(String street, String postalCode) {
        this.street = street;
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCreatedDate(LocalDateTime date) {
        this.createdModifiedDate.setCreatedDate(date);
    }

    public void setModifiedDate(LocalDateTime date) {
        this.createdModifiedDate.setModifiedDate(date);
    }

}
