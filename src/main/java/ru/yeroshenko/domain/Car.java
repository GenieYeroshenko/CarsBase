package ru.yeroshenko.domain;

import javax.persistence.*;


/**
 * Class, that identifies domain entity Car
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

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private CabDriver cabDriver;

    public Car() {
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param cabDriver
     */
    public void setCabDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }

    /**
     *
     * @return cabDriver
     */
    public CabDriver getCabDriver() {
        return cabDriver;
    }

    /**
     *
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return licence plate
     */
    public String getLicencePlate() {
        return licencePlate;
    }

    /**
     *
     * @param licencePlate
     */
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    /**
     *
     * @return car type
     */
    public boolean getCarTypeLorry() {
        return carTypeLorry;
    }

    /**
     *
     * @param carType
     */
    public void setCarTypeLorry(Boolean carType) {
        this.carTypeLorry = carType;
    }

    /**
     *
     * @return car status
     */
    public boolean getCarStatus() {
        return carStatus;
    }

    /**
     *
     * @param carStatus
     */
    public void setCarStatus(Boolean carStatus) {
        this.carStatus = carStatus;
    }

}
