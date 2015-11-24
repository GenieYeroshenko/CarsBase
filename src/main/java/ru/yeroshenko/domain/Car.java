package ru.yeroshenko.domain;

import javax.persistence.*;


/**
 * Entity Class, that identifies Car, which mapped to CabDriver by Many-to-One relationship.
 * According to the relationship many Cars can have the same CabDriver.
 * Car created by CarManager.
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
     * @return id - automatic generates value for the column
     */
    public Long getId() {
        return id;
    }

    /**
     * @param cabDriver Method set CabDriver to Car
     */
    public void setCabDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }

    /**
     * @return cabDriver - one of domain object
     */
    public CabDriver getCabDriver() {
        return cabDriver;
    }

    /**
     * @return model of the Car
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model Method set model to Car
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return licenceplate of the Car
     */
    public String getLicencePlate() {
        return licencePlate;
    }

    /**
     * @param licencePlate Method set licenceplate to Car
     */
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    /**
     * @return car type of the Car
     */
    public boolean getCarTypeLorry() {
        return carTypeLorry;
    }

    /**
     * @param carType Method set type of the Car
     */
    public void setCarTypeLorry(Boolean carType) {
        this.carTypeLorry = carType;
    }

    /**
     * @return car status of the Car
     */
    public boolean getCarStatus() {
        return carStatus;
    }

    /**
     * @param carStatus Method set status serviceability of the Car
     */
    public void setCarStatus(Boolean carStatus) {
        this.carStatus = carStatus;
    }

}
