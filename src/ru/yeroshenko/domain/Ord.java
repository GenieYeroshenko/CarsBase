package ru.yeroshenko.domain;


import javax.persistence.*;
import java.util.Date;


/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name = "Ord")
public class Ord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "carTypeLorry")
    private Boolean carTypeLorry;

    @Column(name = "rout")
    private String rout;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;

    @Enumerated(EnumType.STRING)
    private OrdStatus ordStatus;

    public Ord() {
    }

    public Long getId() {
        return id;
    }

    public OrdStatus getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(OrdStatus ordStatus) {
        this.ordStatus = ordStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getCarTypeLorry() {
        return carTypeLorry;
    }

    public void setCarTypeLorry(Boolean carType) {
        this.carTypeLorry = carType;
    }

    public String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public enum OrdStatus {

        ASSIGNED, IN_QUEUE, IN_TRANSIT, DONE;
    }
}
