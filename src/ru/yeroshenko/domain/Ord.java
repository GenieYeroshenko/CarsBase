package ru.yeroshenko.domain;

import sun.util.calendar.LocalGregorianCalendar.*;

import javax.persistence.*;


/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="Ord")
public class Ord {

    private Long id;
    private OrdStatus ordStatus;
    private Date date;
    private CabDriver cabDriver;
    private CarType carType;
    private String rout;


    public Ord() {
        rout = null;
    }

    public Ord(Ord ord) {
        rout = ord.getRout();
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
    private String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }


}
