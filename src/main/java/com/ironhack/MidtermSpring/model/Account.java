package com.ironhack.MidtermSpring.model;

/*
Account_id INT NOT NULL AUTO_INCREMENT,
Accounts_name VARCHAR(255),
date_opened DATE,
PRIMARY KEY (Account_id),
FOREIGN KEY(Account_Types_id REFERENCES Account_types(Account_Types_id));
 */

import com.ironhack.MidtermSpring.classes.Money;
import com.ironhack.MidtermSpring.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money balance;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolder secondaryOwner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    private final Money penaltyFee;
    @NotNull

    private String secretKey;


    protected LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;


    @OneToMany(mappedBy = "sendingAccount")
    private Set<Transaction> transactionsDone = new HashSet<>();
    @OneToMany(mappedBy = "receivingAccount")
    private Set<Transaction> transactionsReceived = new HashSet<>();

    // CONSTRUCTORS
    public Account() {
        this.penaltyFee = new Money(new BigDecimal(40));
        this.balance = new Money(new BigDecimal(0));
        this.status = Status.ACTIVE;
        this.transactionsDone = new HashSet<>();
        this.transactionsReceived = new HashSet<>();
    }

    public Account(Money balance, AccountHolder primaryOwner, String secretKey, LocalDate creationDate) {
        this.balance = balance;
        this.penaltyFee = new Money(new BigDecimal(40));
        this.primaryOwner = primaryOwner;
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = Status.ACTIVE;
        this.transactionsDone = new HashSet<>();
        this.transactionsReceived = new HashSet<>();
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                   String secretKey, LocalDate creationDate) {
        this.balance = balance;
        this.penaltyFee = new Money(new BigDecimal(40));
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = Status.ACTIVE;
        this.transactionsDone = new HashSet<>();
        this.transactionsReceived = new HashSet<>();
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Transaction> getTransactionsDone() {
        return transactionsDone;
    }

    public void setTransactionsDone(Set<Transaction> transactionsDone) {
        this.transactionsDone = transactionsDone;
    }

    public Set<Transaction> getTransactionsReceived() {
        return transactionsReceived;
    }

    public void setTransactionsReceived(Set<Transaction> transactionsReceived) {
        this.transactionsReceived = transactionsReceived;
    }
}





