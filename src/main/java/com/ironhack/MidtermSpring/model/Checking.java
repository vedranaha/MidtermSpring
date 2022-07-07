package com.ironhack.MidtermSpring.model;

/*
TABLE Checking_Accounts(
Checking_Accounts_id INT NOT NULL AUTO_INCREMENT ,
Balance DOUBLE,
secretKey BIGINT,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
MinimumBalance DOUBLE,
PenaltyFee DOUBLE,
MonthlyMaintenanceFee DOUBLE,
CreationDate DATE,
Status VARCHAR(255),
PRIMARY KEY (Checking_Accounts_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));
 */

import com.ironhack.MidtermSpring.classes.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private final Money minimumBalance; //250

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    private final Money monthlyMaintenanceFee; //12

    private LocalDate lastTimeMaintenanceFeeApplied;

    // CONSTRUCTORS
    public Checking() {
        this.lastTimeMaintenanceFeeApplied = this.creationDate;
        this.monthlyMaintenanceFee = new Money(new BigDecimal(12));
        this.minimumBalance = new Money(new BigDecimal(250));
    }

    public Checking(Money balance, AccountHolder primaryOwner, String secretKey, LocalDate creationDate) {
        super(balance, primaryOwner, secretKey, creationDate);
        this.lastTimeMaintenanceFeeApplied = this.creationDate;
        this.monthlyMaintenanceFee = new Money(new BigDecimal(12));
        this.minimumBalance = new Money(new BigDecimal(250));
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey,
                    LocalDate creationDate) {
        super(balance, primaryOwner, secondaryOwner, secretKey, creationDate);
        this.lastTimeMaintenanceFeeApplied = this.creationDate;
        this.monthlyMaintenanceFee = new Money(new BigDecimal(12));
        this.minimumBalance = new Money(new BigDecimal(250));
    }

    // GETTERS & SETTERS
    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public LocalDate getLastTimeMaintenanceFeeApplied() {
        return lastTimeMaintenanceFeeApplied;
    }

    public void setLastTimeMaintenanceFeeApplied(LocalDate lastTimeMaintenanceFeeApplied) {
        this.lastTimeMaintenanceFeeApplied = lastTimeMaintenanceFeeApplied;
    }

    // Deduct penalty fee if the balance is below minimum
    @Override
    public void setBalance(Money balance) {
        super.setBalance(balance);
        Money actualBalance = new Money(getBalance().getAmount(), getBalance().getCurrency());
        actualBalance.decreaseAmount(minimumBalance);
        if (actualBalance.getAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
            balance.decreaseAmount(super.getPenaltyFee());
        }
    }}
