package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",length = 255, nullable = false)
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateprovinceId")
    private StateProvince stateprovince;

    public City(String name, StateProvince stateprovince) {
        this.name = name;
        this.stateprovince = stateprovince;
    }
}
