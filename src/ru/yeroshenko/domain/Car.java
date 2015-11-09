package ru.yeroshenko.domain;

import javax.persistence.*;


/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="Car")
public class Car {

    private Long id;
    private String model;
    private String licencePlate;
    private CarType carType;
    private Boolean carStatus;


    public Car() {
        model = null;
    }

    public Car(Car car) {
        model = car.getModel();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "model")
    private String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
