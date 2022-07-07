package com.ironhack.MidtermSpring.model;

/*
User_Types_code VARCHAR(15),
Account_holders_name VARCHAR(15),
PrimaryAddress VARCHAR(255),
mailingAddress VARCHAR(255),
PRIMARY KEY (accountHoldersId),
FOREIGN KEY(UserTypeId) REFERENCES UserTypes(UserTypeId));
 */
import com.ironhack.MidtermSpring.classes.Address;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {
    @NotNull(message = "Date can not be null")
    private Date dateOfBirth;

    @NotNull(message = "Primary Address can not be null")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "primary_address_street")),
            @AttributeOverride(name = "homeNumber", column = @Column(name = "primary_address_home_number")),
            @AttributeOverride(name = "city", column = @Column(name = "primary_address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "primary_address_postal_code")),

    })
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailing_address_street")),
            @AttributeOverride(name = "homeNumber", column = @Column(name = "mailing_address_home_number")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_address_postal_code")),

    })
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")

    private Set<Account> primaryAccountSet;

    @OneToMany(mappedBy = "secondaryOwner")

    private Set<Account> secondaryAccountSet;

    //CONSTRUCTORS
    public AccountHolder() {
    }

    public AccountHolder(String username, String password, Role role, Date dateOfBirth, Address primaryAddress) {
        super(username, password, role);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder(String username, String password, Role role, Date dateOfBirth, Address primaryAddress,
                         Address mailingAddress) {
        super(username, password, role);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    // GETTERS & SETTERS
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }
    //if is equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountHolder that = (AccountHolder) o;
        return dateOfBirth.equals(that.dateOfBirth) && primaryAddress.equals(that.primaryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateOfBirth, primaryAddress);
    }
}

