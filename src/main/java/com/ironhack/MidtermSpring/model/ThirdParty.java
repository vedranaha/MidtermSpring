package com.ironhack.MidtermSpring.model;

/*
ThirdParty_id INT NOT NULL AUTO_INCREMENT,
User_Type_id VARCHAR(15),
ThirdParty_name VARCHAR(15),
HashedKey VARCHAR(32),
PRIMARY KEY (ThirdParty_id),
FOREIGN KEY(User_Types_code) REFERENCES User_types(User_Types_code));
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Hashed key can not be null")

    private String hashedKey;
    @NotNull(message = "Name can not be null")
    private String name;

    @OneToMany(mappedBy = "thirdParty")
    private Set<Transaction> transactionsDone = new HashSet<>();

    //CONSTRUCTORS
    public ThirdParty() {
    }

    public ThirdParty(String hashedKey, String name) {
        this.hashedKey = hashedKey;
        this.name = name;
    }

    //GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transaction> getTransactionsDone() {
        return transactionsDone;
    }

    public void setTransactionsDone(Set<Transaction> transactionsDone) {
        this.transactionsDone = transactionsDone;
    }

    //Is equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdParty that = (ThirdParty) o;
        return hashedKey.equals(that.hashedKey) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashedKey, name);
    }
}
