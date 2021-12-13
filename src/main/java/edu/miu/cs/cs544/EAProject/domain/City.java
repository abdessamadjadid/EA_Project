package edu.miu.cs.cs544.EAProject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(LocalDateTime date) {
        this.createdModifiedDate.setCreatedDate(date);
    }

    public void setModifiedDate(LocalDateTime date) {
        this.createdModifiedDate.setModifiedDate(date);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

}
