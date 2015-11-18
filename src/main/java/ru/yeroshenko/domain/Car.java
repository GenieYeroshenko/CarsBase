package ru.yeroshenko.domain;

import javax.persistence.*;


/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "licencePlate")
    private String licencePlate;

    @Column(name = "carType")
    private Boolean carTypeLorry;

    @Column(name = "carStatus")
    private Boolean carStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CabDriver cabDriver;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setCabDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }

    public CabDriver getCabDriver() {
        return cabDriver;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public boolean getCarTypeLorry() {
        return carTypeLorry;
    }

    public void setCarTypeLorry(Boolean carType) {
        this.carTypeLorry = carType;
    }

    public boolean getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Boolean carStatus) {
        this.carStatus = carStatus;
    }

}
