package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @OrderBy("postalCode")
    private List<Address> addresses;

    public City(String name, List<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }
}
