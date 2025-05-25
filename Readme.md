# 🏦 ATM System with Encapsulation (Java + MySQL)

A simple GUI-based ATM system built using Java Swing with encapsulation principles. This project supports user registration, login, balance inquiry, deposit, and withdrawal, all backed by a MySQL database.

---

## 📂 Project Structure
ATM-Management/
├── src/
│ ├── dao/
│ │ └── UserDAO.java
│ ├── model/
│ │ └── User.java
│ ├── ui/
│ │ ├── LoginUI.java
│ │ ├── RegisterUI.java
│ │ └── ATMUI.java
│ └── utils/
│ └── DBConnection.java
├── Main.java
└── README.md

---

## 🧠 Key OOP Concept: Encapsulation
Encapsulation is used to:

- Protect the internal state of the `User`  object
- Expose only the necessary methods (getters/setters)
- Hide implementation details in `UserDAO`  from the UI components
---

## 💻 Technologies Used
| Tool | Purpose |
| ----- | ----- |
| Java | Core programming language |
| Swing | GUI components |
| MySQL | Database |
| JDBC | Database connectivity |
| OOP | <p>Encapsulation with </p><p> fields, getters/setters</p> |
| IntelliJ / Eclipse | Recommended IDEs |
---

## 🚀 Features
- 👤 **User Registration** with username and password
- 🔐 **User Login** with validation
- 💰 **View Account Balance**
- ➕ **Deposit Funds**
- ➖ **Withdraw Funds**
- 🚪 **Exit & Logout**
---

## 🛠️ Setup Instructions
### 1. Clone the Repo
```bash
git clone https://github.com/theankitmaurya/ATM-System.git
```
### 2. Set Up MySQL
Run this SQL:

```
CREATE DATABASE atm_db;

USE atm_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    balance DOUBLE DEFAULT 0.0
);
```
### 3. Add MySQL JDBC Connector
Download the [﻿MySQL Connector JAR](https://dev.mysql.com/downloads/connector/j/) and add it to your project dependencies.

4. Update DB Credentials in `DBConnection.java` 

```
private static final String URL = "jdbc:mysql://localhost:3306/atm_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```
### 5. Run the Application
Run `Main.java` to launch the Login screen.

