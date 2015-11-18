package ru.yeroshenko.domain;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="Trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    private Date date;
//todo Ord
    @Column(name = "rout")
    private String rout;

    @Column(name = "carTypeLorry")
    private Boolean carTypeLorry;

    @Column(name = "tripStatus")
    private Boolean tripStatus;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Ord ord;

    public Trip() {
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }

    public Boolean getCarTypeLorry() {
        return carTypeLorry;
    }

    public void setCarTypeLorry(Boolean carTypeLorry) {
        this.carTypeLorry = carTypeLorry;
    }

    public Boolean getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(Boolean tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Ord getOrd() {
        return ord;
    }

    public void setOrd(Ord ord) {
        this.ord = ord;
    }
}
