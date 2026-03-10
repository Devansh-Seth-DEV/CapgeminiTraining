# JPA (Jakarta Persistence API)

**Jakarta Persistence API (JPA)**, formerly known as Java Persistence API, is a standard specification for **Object-Relational Mapping (ORM)** in Java applications.

JPA allows you to perform database operations using Java code and annotations.

### Analogy
Imagine a *Restaurant Standard* that says:

- Every restaurant must have a Menu.

- Customers must be able to Order.

- The restaurant must provide a Bill.

JPA is that **Standard**. It defines what an `EntityManager` (the waiter) and a `Query` (the order) should look like.

**Hibernate** or **EclipseLink** are the actual *Restaurants* (like Pizza Hut or Domino's). They might cook the food differently, but they all follow the standard rules of having a menu and giving you a bill.

### Doubt Queries

#### 1. Is it the rules/protocols?
Yes, exactly. It is a specification. Think of it as a detailed manual that describes how an **ORM (Object-Relational Mapping)** tool should behave in the Java world.

#### 2. Is it a framework?
Not exactly. JPA itself doesn't "do" the work, it just defines the **Interfaces** and **Annotations** (like `@Entity` or `@Id`). A framework like *Hibernate* is the actual engine that follows those rules to move data.

#### 3. Is it a standard?
Yes. Just like Java has standards for naming conventions (TitleCase), it has the Jakarta EE standards for how applications should talk to databases. JPA is that standard.

#### 4. Does Hibernate replace JDBC?
Actually, Hibernate uses JDBC under the hood! When you tell Hibernate to *Save this User*, it generates a JDBC `PreparedStatement` and executes it. It doesn't replace JDBC, it sits on top of it to make your life easier.
