package ru.yeroshenko.domain;

import javax.persistence.*;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@Table(name="OrderStatus")
public class OrderStatus {

    String assigned = "assigned";
    String inQueue = "inQueue";
    String inTransit = "inTransit";
    String done = "done";


}
