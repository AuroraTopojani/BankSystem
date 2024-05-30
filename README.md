# Spring Boot Banking System

## Overview

This is a simple banking system application built with Java and Spring Boot. It allows you to create banks, accounts, and perform transactions between accounts. The application also supports operations such as deposit and withdrawal of funds.

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven
- An IDE such as Visual Studio Code, IntelliJ IDEA, or Eclipse

## Setup Instructions

### Clone the Repository

git clone https://github.com/yourusername/banking-system.git
cd banking-system

## Build the Application

Open the project directory in your terminal and run the following command to build the project:

-mvn clean install

## Run the Application

-mvn spring-boot:run

## Endpoints

The application has the following RESTful endpoints

### Create a Bank

POST /api/banks

Request body :
{
"name": "My Bank",
"flatFee": 1.0,
"percentFee": 2.0
}

### Create an Account

POST /api/banks/{bankId}/accounts

Request body :
{
"name": "Account 1",
"balance": 1000.0
}

### Transfer Money

POST /api/banks/{bankId}/transfer

Request body :

{
"fromAccountId": 1,
"toAccountId": 2,
"amount": 100.0,
"reason": "Payment",
"flatFee": true
}

### Desposit money

POST /api/accounts/{accountId}/deposit?amount=100.0

### Withdraw money

POST /api/accounts/{accountId}/withdraw?amount=50.0

### Get Account Balance

GET /api/accounts/{accountId}/balance

### Get Accounts of a Bank

GET /api/banks/{bankId}/accounts

### Get Transaction of an Account

GET /api/accounts/{accountId}/transactions

### Get Total Transaction Fees of a Bank

GET /api/banks/{bankId}/total-fees

### Get Total Transfer Amount of a Bank

GET /api/banks/{bankId}/total-transfer-amount

## Example Usage

Here are some example cURL commands to interact with the API:

### Create a Bank:

curl -X POST http://localhost:8080/api/banks -H "Content-Type: application/json" -d '{"name": "My Bank", "flatFee": 1.0, "percentFee": 2.0}'

### Transfer money:

curl -X POST http://localhost:8080/api/banks/1/transfer -H "Content-Type: application/json" -d '{"fromAccountId": 1, "toAccountId": 2, "amount": 100.0, "reason": "Payment", "flatFee": true}'

### Get Account Balance:

curl http://localhost:8080/api/accounts/1/balance

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgments

Spring Boot documentation: https://spring.io/projects/spring-boot
Java Development Kit (JDK): https://openjdk.java.net/
