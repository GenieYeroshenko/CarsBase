package ru.yeroshenko.domain;

import javax.persistence.*;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="CarType")
public class CarType {

    private long id;
    private String passengerCar;
    private String lorry;


    public CarType() {

    }

    public CarType(CarType carType) {
        id = carType.getId();
        passengerCar = carType.getPassengerCar();
        lorry = carType.getLorry();

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

    @Column(name = "PassengerCar")
    public String getPassengerCar() {
        return passengerCar;
    }
    public void setPassengerCar(String passengerCar) {
        this.passengerCar = passengerCar;
    }

    @Column(name = "Lorry")
    public String getLorry() {
        return lorry;
    }
    public void setLorry(String lorry) {
        this.lorry = lorry;
    }
}
