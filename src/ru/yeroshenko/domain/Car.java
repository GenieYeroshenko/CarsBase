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
    //private CarType carType;
   // private Boolean carStatus;


    public Car() {
        //model = null;
    }

    public Car(Car car) {
        model = car.getModel();
        id = car.getId();
        licencePlate = car.getLicencePlate();
        //carType = car.getCarType();
        //carStatus = car.getCarStatus();

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

    @Column(name = "licencePlate")
    private String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

//    @Column(name="carType")
//    private CarType getCarType() {
//        return carType;
//    }
//
//    public void setCarType(CarType carType) {
//        this.carType = carType;
//    }
//
//    @Column(name="carStatus")
//    private boolean getCarStatus() {
//        return carStatus;
//    }
//
//    public void setCarStatus(boolean carStatus) {
//        this.carStatus = carStatus;
//    }

}
