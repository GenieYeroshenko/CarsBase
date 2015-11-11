package ru.yeroshenko.domain;

import javax.persistence.*;
import sun.util.calendar.LocalGregorianCalendar.Date;
/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="Trip")
public class Trip {

    private Long id;
    private Date date;
    private String rout;
    private CarType carType;
    private String tripStatus;


    public Trip() {

    }

    public Trip(Trip trip) {
        rout = trip.getRout();
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


    @Column(name = "rout")
    public String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }
}
