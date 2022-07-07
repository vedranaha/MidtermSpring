package com.ironhack.MidtermSpring.model;

import com.ironhack.MidtermSpring.classes.Money;
import com.ironhack.MidtermSpring.enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Money amount;

    @ManyToOne
    private Account sendingAccount;
    @ManyToOne
    private Account receivingAccount;
    @ManyToOne
    private ThirdParty thirdParty;

    //CONSTRUCTORS
    public Transaction() {
    }

    public Transaction(LocalDateTime transactionDate, Money amount, Account sendingAccount, Account receivingAccount) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.transactionType = TransactionType.TRANSFER;
    }
    public Transaction(LocalDateTime transactionDate, Money amount, Account receivingAccount, ThirdParty thirdParty,
                       TransactionType transactionType) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.receivingAccount = receivingAccount;
        this.thirdParty = thirdParty;
        this.transactionType = transactionType;
    }

    //GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Account getSendingAccount() {
        return sendingAccount;
    }

    public void setSendingAccount(Account sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public Account getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(Account receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public ThirdParty getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(ThirdParty thirdParty) {
        this.thirdParty = thirdParty;
    }
}