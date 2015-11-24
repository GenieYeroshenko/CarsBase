package ru.yeroshenko.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class, that identifies domain entity CabDriver
 */
@Entity
@DiscriminatorValue("CabDriver")
public class CabDriver extends Account {
}

