package com.ironhack.MidtermSpring.model;


import com.ironhack.MidtermSpring.classes.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    // CONSTRUCTORS
    public StudentChecking() {
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, String secretKey, LocalDate creationDate) {
        super(balance, primaryOwner, secretKey, creationDate);
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey,
                           LocalDate creationDate) {
        super(balance, primaryOwner, secondaryOwner, secretKey, creationDate);
    }

}