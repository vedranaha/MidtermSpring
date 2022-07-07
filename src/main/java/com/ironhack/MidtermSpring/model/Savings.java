package com.ironhack.MidtermSpring.model;

/*
CREATE TABLE Savings(
Account_Types_code VARCHAR(15),
Savings_id INT NOT NULL AUTO_INCREMENT ,
Balance DOUBLE,
secretKey BIGINT,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
MinimumBalance DOUBLE,
PenaltyFee DOUBLE,
InterestRate DOUBLE,
CreationDate DATE,
PRIMARY KEY (Savings_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));
*/

import com.ironhack.MidtermSpring.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance; //default 1000, min 100 when instantiated

    @DecimalMax(value = "0.5", message = "The interest rate can not be lower than 0.5")
    private BigDecimal interestRate; //Default 0.0025, max 0.5

    private LocalDate lastTimeInterestApplied;

    // CONSTRUCTORS
    public Savings() {
        this.minimumBalance = new Money(new BigDecimal(1000));
        this.interestRate = new BigDecimal("0.0025");
        this.lastTimeInterestApplied = this.creationDate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, String secretKey, Money minimumBalance,
                   BigDecimal interestRate, LocalDate creationDate) {
        super(balance, primaryOwner, secretKey, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.lastTimeInterestApplied = this.creationDate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                   String secretKey, Money minimumBalance, BigDecimal interestRate, LocalDate creationDate) {
        super(balance, primaryOwner, secondaryOwner, secretKey, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.lastTimeInterestApplied = this.creationDate;
    }

    // GETTERS & SETTERS
    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public LocalDate getLastTimeInterestApplied() {
        return lastTimeInterestApplied;
    }

    public void setLastTimeInterestApplied(LocalDate lastTimeInterestApplied) {
        this.lastTimeInterestApplied = lastTimeInterestApplied;
    }

    // Deduct penalty fee if balance is below minimum
    @Override
    public void setBalance(Money balance) {
        super.setBalance(balance);
        Money actualBalance = new Money(getBalance().getAmount(),getBalance().getCurrency());
        actualBalance.decreaseAmount(minimumBalance);
        if (actualBalance.getAmount().compareTo(BigDecimal.valueOf(0)) < 0){
            balance.decreaseAmount(super.getPenaltyFee());
        }
    }
}
