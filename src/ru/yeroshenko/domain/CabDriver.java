package ru.yeroshenko.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name = "CabDriver")
public class CabDriver {
    private Long id;
    private String name;


    private List<Car> cars = new ArrayList<Car>();


    public CabDriver() {
    }

    public CabDriver(CabDriver cabDriver) {
        name = cabDriver.getName();
        id = cabDriver.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cabDriver")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

