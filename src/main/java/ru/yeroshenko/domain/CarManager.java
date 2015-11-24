package ru.yeroshenko.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class, that identifies domain entity CarManager
 */
@Entity
@DiscriminatorValue("CarManager")
public class CarManager extends Account {
}

