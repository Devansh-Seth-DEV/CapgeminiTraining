# Mess Management System (Hibernate & MySQL)

This project is a Java-based management system that tracks suppliers and the items they provide using **Hibernate ORM** and **MySQL**.

## Prerequisites

Before you begin, ensure you have the following installed:

- MySQL Server (8.0 or higher)

- Java JDK 17+

- IDE (Eclipse, IntelliJ, or VS Code)

- Maven

## Database Setup

Follow these steps to prepare your relational database.

#### Step A: Access MySQL
Open your MySQL Command Line Client or a tool like MySQL Workbench and log in:

```bash
mysql -u root -p
```

#### Step B: Execute the SQL Scripts
Copy and paste the following commands to create the database schema:

```sql
-- Create the database
CREATE DATABASE MessManagement;
USE MessManagement;

-- Create Supplier Table
CREATE TABLE Supplier (
    Sid INT PRIMARY KEY,
    Sname VARCHAR(100),
    Srank DECIMAL(3, 1),
    city VARCHAR(50)
);

-- Create Item Table
CREATE TABLE Item (
    itemid INT PRIMARY KEY,
    name VARCHAR(100),
    Type VARCHAR(50)
);

-- Create Supply Table
CREATE TABLE Supply (
    billno INT PRIMARY KEY,
    Sid INT,
    itemid INT,
    datesupply DATE,
    Price DECIMAL(10, 2),
    qty INT,
    FOREIGN KEY (Sid) REFERENCES Supplier(Sid),
    FOREIGN KEY (itemid) REFERENCES Item(itemid)
);
```

## Project Configuration
Once the database is ready:

- **Configure Credentials:** Open `src/main/resources/META-INF/persistence.xml.`

- **Update Database Details:**

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/MessManagement" />
<property name="jakarta.persistence.jdbc.user" value="your_username" />
<property name="jakarta.persistence.jdbc.password" value="your_password" />
```

## How to Run

- **Update the Project:** For Eclipse IDE, Press `Alt + F5` and update the project. This will install required dependencies for you!

- **Run the App:** Execute the `App.java` file.

  - The application will first populate the database using the `MessDAO.populateDatabase()` method.

  - It will then execute several **HQL (Hibernate Query Language)** queries to retrieve supplier names, filter by city, and find items by specific suppliers.
