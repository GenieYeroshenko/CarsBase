package ru.yeroshenko.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Genie Yeroshenko on 08/11/15.
 */
@Entity
@DiscriminatorValue("CarManager")
public class CarManager extends Account {
}

