package ru.yeroshenko.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity Class, that identifies CabDriver, which extends Account
 */
@Entity
@DiscriminatorValue("CabDriver")
public class CabDriver extends Account {
}

