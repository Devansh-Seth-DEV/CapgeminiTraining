# Spring Boot Training Management Assignment

## Overview

This assignment involves building a **Spring Boot REST application** that manages training sessions. The application will interact with a **MySQL database** using **Spring Data JPA** and expose REST endpoints to retrieve training information based on different criteria.

---

## Project Dependencies

Create a Spring Boot project with the following dependencies:

- **Spring Web** – for building RESTful APIs  
- **Spring Data JPA** – for database interaction using ORM  
- **MySQL Driver** – for connecting to the MySQL database  

---

## Database Configuration

### Database Name

`trainingdb`

### Table: `training`

Create a table with the following columns:

| Column Name   | Description                                  |
|:------------- |:-------------------------------------------- |
| `trainingid`  | Primary key, auto-generated using a sequence |
| `topic`       | Topic of the training                        |
| `startDate`   | Start date of the training                   |
| `endDate`     | End date of the training                     |
| `trainername` | Name of the trainer conducting the session   |
| `venue`       | Location where the training is held          |

> [!NOTE]  
> Insert **at least 6 records** into the table:
> 
> - **2 trainings** that have **already finished**
> - **2 trainings** that are **currently ongoing**
> - **2 trainings** that are **upcoming in the near future**

---

## Application Requirements

### 1. Entity Creation

Create a **JPA Entity class** representing the `training` table.

---

### 2. REST API Endpoints

#### 1. Get Ongoing and Upcoming Trainings

**Endpoint**

```
/trainings
```

**Description**

Returns all trainings that are either:

- currently ongoing, or
- scheduled for the future.

**Response Format**

- JSON (recommended) or
- String format.

---

#### 2. Search Ongoing or Upcoming Trainings by Topic

**Endpoint**

```
/trainings?name=spring
```

**Description**

Returns trainings that:

- are **ongoing or upcoming**, and
- contain the keyword **"spring"** in the **topic**.

---

#### 3. Search Upcoming Trainings by Topic

**Endpoint**

```
/trainings/upcoming?name=spring
```

**Description**

Returns trainings that:

- are **upcoming only**, and
- contain the keyword **"spring"** in the **topic**.
