package ru.yeroshenko.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name = "CabDriver")
@DiscriminatorValue("CabDriver")
public class CabDriver extends Account {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;

    @Column(name = "name")
    private String name;

    public CabDriver() {
    }

//    public Long getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

