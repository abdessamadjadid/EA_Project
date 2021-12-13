package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private CreatedModifiedDate createdModifiedDate;

    public Address(String street, String postalCode) {
        this.street = street;
        this.postalCode = postalCode;
    }
}
