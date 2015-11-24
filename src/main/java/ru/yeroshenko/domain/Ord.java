package ru.yeroshenko.domain;


import javax.persistence.*;
import java.time.LocalDate;


/**
 * Class, that identifies domain entity Order
 */
@Entity
@Table(name = "Ord")
public class Ord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "carTypeLorry")
    private Boolean carTypeLorry;

    @Column(name = "rout")
    private String rout;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = true, fetch = FetchType.EAGER)
    private Car car;

    @Enumerated(EnumType.STRING)
    private OrdStatus ordStatus;

    public Ord() {
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return ordStatus
     */
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    /**
     *
     * @param ordStatus
     */
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    /**
     *
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     *
     * @return carType
     */
    public Boolean getCarTypeLorry() {
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
     * @return rout
     */
    public String getRout() {
        return rout;
    }

    /**
     *
     * @param rout
     */
    public void setRout(String rout) {
        this.rout = rout;
    }

    /**
     *
     * @return car
     */
    public Car getCar() {
        return car;
    }

    /**
     *
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Class, that uses only Enums
     */
    public enum OrdStatus {

        IN_QUEUE, ASSIGNED, IN_TRANSIT, DONE
    }
}
