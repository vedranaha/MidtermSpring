DROP SCHEMA IF EXISTS bank;
CREATE SCHEMA bank;
USE bank;

DROP TABLE IF EXISTS Account_types;
-- StudentChecking, Checking, Savings, and CreditCard
CREATE TABLE Account_types(
Account_Types_code VARCHAR(15),
Account_Types_description VARCHAR(255),
PRIMARY KEY (Account_Types_code));

DROP TABLE IF EXISTS Accounts;
CREATE TABLE Accounts(
Account_id INT NOT NULL AUTO_INCREMENT,
Account_Types_code VARCHAR(15),
Accounts_name VARCHAR(255),
date_opened DATE,
PRIMARY KEY (Account_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));

DROP TABLE IF EXISTS Checking_Accounts;
-- A balance,A secretKey, A PrimaryOwner, An optional SecondaryOwner, A minimumBalance, A penaltyFee, A monthlyMaintenanceFee,A creationDate, status (FROZEN, ACTIVE)--
CREATE TABLE Checking_Accounts(
Checking_Accounts_id INT NOT NULL AUTO_INCREMENT ,
Account_Types_code VARCHAR(15),
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

DROP TABLE IF EXISTS Student_Checking;
CREATE TABLE Student_Checking(
Student_Checking_id INT NOT NULL AUTO_INCREMENT,
Account_Types_code VARCHAR(15),
Balance DOUBLE,
secretKey BIGINT,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
PenaltyFee DOUBLE,
CreationDate DATE,
PRIMARY KEY (Student_Checking_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));

DROP TABLE IF EXISTS Savings;
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

DROP TABLE IF EXISTS CreditCard;
CREATE TABLE CreditCard(
CreditCard_id INT NOT NULL AUTO_INCREMENT,
Account_Types_code VARCHAR(15),
Balance DOUBLE,
PrimaryOwner VARCHAR(255),
SecondaryOwner VARCHAR(255),
CreditLimit DOUBLE,
InterestRate DOUBLE,
PenaltyFee DOUBLE,
PRIMARY KEY (CreditCard_id),
FOREIGN KEY(Account_Types_code) REFERENCES Account_types(Account_Types_code));

DROP TABLE IF EXISTS User_types;
-- AccountHolders, Admins, ThirdParty
CREATE TABLE User_types(
User_Types_code VARCHAR(15),
User_Types_description VARCHAR(255),
PRIMARY KEY (User_Types_code));

DROP TABLE IF EXISTS Account_holders;
CREATE TABLE Account_holders(
Account_holders_id INT NOT NULL AUTO_INCREMENT,
User_Types_code VARCHAR(15),
Account_holders_name VARCHAR(15),
PrimaryAddress VARCHAR(255),
mailingAddress VARCHAR(255),
PRIMARY KEY (Account_holders_id),
FOREIGN KEY(User_Types_code) REFERENCES User_types(User_Types_code));

DROP TABLE IF EXISTS Admins;
CREATE TABLE Admins(
Admins_id INT NOT NULL AUTO_INCREMENT,
User_Types_code VARCHAR(15),
Admins_name VARCHAR(15),
PRIMARY KEY (Admins_id),
FOREIGN KEY(User_Types_code) REFERENCES User_types(User_Types_code));

DROP TABLE IF EXISTS ThirdParty;
CREATE TABLE ThirdParty(
ThirdParty_id INT NOT NULL AUTO_INCREMENT,
User_Types_code VARCHAR(15),
ThirdParty_name VARCHAR(15),
HashedKey VARCHAR(32),
PRIMARY KEY (ThirdParty_id),
FOREIGN KEY(User_Types_code) REFERENCES User_types(User_Types_code));

