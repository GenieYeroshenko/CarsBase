package ru.yeroshenko.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity Class, that identifies CarManager, which extends AccountClass
 */
@Entity
@DiscriminatorValue("CarManager")
public class CarManager extends Account {
}

