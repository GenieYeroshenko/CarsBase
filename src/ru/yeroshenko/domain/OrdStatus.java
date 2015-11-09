package ru.yeroshenko.domain;

import javax.persistence.*;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="OrdStatus")
public class OrdStatus {

    private long id;
    private String assigned;
    private String inQueue;
    private String inTransit;
    private String done;

    public OrdStatus() {
    }


    public OrdStatus(OrdStatus ordStatus) {
        id = ordStatus.getId();
        assigned = ordStatus.getAssigned();
        inQueue = ordStatus.getInQueue();
        inTransit = ordStatus.getInTransit();
        done = ordStatus.getDone();
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

    @Column(name = "Assigned")
    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
    @Column(name = "InQueue")
    public String getInQueue() {
        return inQueue;
    }

    public void setInQueue(String inQueue) {
        this.inQueue = inQueue;
    }
    @Column(name = "InTransit")
    public String getInTransit() {
        return inTransit;
    }

    public void setInTransit(String inTransit) {
        this.inTransit = inTransit;
    }

    @Column(name = "Done")
    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }


}
