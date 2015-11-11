package ru.yeroshenko.domain;

import javax.persistence.*;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="CabDriver")
public class CabDriver {
    private Long id;
    private String name;


        public CabDriver() {
        }

        public CabDriver(CabDriver cabDriver) {
            name = cabDriver.getName();
            id = cabDriver.getId();
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

        @Column(name = "name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}

