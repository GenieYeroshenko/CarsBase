package ru.yeroshenko.domain;

import javax.persistence.*;

/**
 * Entity Class, that generates methods to set and get parameters for abstract Account
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Account")
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    /**
     * @return id - automatic generates value for the column
     */
    public long getId() {
        return id;
    }

    /**
     * @return login of the User
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login - unique login of the User
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return password of the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password - unique login of the User
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
