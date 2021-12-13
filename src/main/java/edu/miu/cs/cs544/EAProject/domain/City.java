package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @OrderBy("postalCode")
    private List<Address> addresses;

    public City(String name, List<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }
}
