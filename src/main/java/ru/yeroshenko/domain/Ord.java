package ru.yeroshenko.domain;


import javax.persistence.*;
import java.time.LocalDate;


/**
 * Entity Class, that identifies Order, wich created by CarManager
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
     * @return id - automatic generates value for the column
     */
    public Long getId() {
        return id;
    }

    /**
     * @return ordStatus - status of the Ord
     */
    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    /**
     * @param ordStatus - status of the Order
     *                  Method set status of the Ord to "in queue", "assigned", "in transit" or "done"
     */
    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    /**
     * @return date, that is automatically generated
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date - locale date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return carType (lorry or passenger car)
     */
    public Boolean getCarTypeLorry() {
        return carTypeLorry;
    }

    /**
     * @param carType Method set status type of the Car
     */
    public void setCarTypeLorry(Boolean carType) {
        this.carTypeLorry = carType;
    }

    /**
     * @return rout of the Order
     */
    public String getRout() {
        return rout;
    }

    /**
     * @param rout Method set rout to the Order
     */
    public void setRout(String rout) {
        this.rout = rout;
    }

    /**
     * @return car with all parameters
     */
    public Car getCar() {
        return car;
    }

    /**
     * @param car Method set Car to the Order
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Class, that uses only Enums to define status of the Order
     */
    public enum OrdStatus {

        IN_QUEUE, ASSIGNED, IN_TRANSIT, DONE
    }
}
