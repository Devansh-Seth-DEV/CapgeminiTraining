# Reflection API

The **Reflection API** 🪞 is one of Java's most powerful features. It allows a program to inspect and manipulate itself at runtime.

Normally, Java is statically typed—you know the names of your classes, methods, and variables when you write the code. Reflection lets you look at a class and say, *I don't know what you are yet, but tell me what methods you have*, and then execute them on the fly.

### 🧩 Pre-requisites
Before we dive into the code, Reflection relies heavily on a few core Java concepts.

- **The Class Hierarchy:** Understanding that every object in Java inherits from `java.lang.Object`.

- **Access Modifiers:** Knowledge of `public`, `private`, and `protected` (Reflection can actually bypass these!).

- **Exceptions:** Reflection code is *checked-exception heavy*, meaning you'll deal with things like `ClassNotFoundException` or `NoSuchMethodException`.

- **Generics:** How Java handles types like `List<String>` vs `List<Integer>`.

## The Entry Point: The `Class<T>` Object

To inspect a class, you first need to get hold of its **blueprint**. In Java, this blueprint is an instance of `java.lang.Class`. Every type in Java (classes, interfaces, enums, and even primitives like int) has a corresponding `Class` object managed by the *JVM*.

Think of the `Class<T>` object as an **ID Card** for any Java class.

In a normal Java program, you use a blueprint (the code you wrote) to build a house (an object). But once the house is built, the program **forgets** the details of the blueprint.

**Reflection** allows the program to look at that house and pull out the original blueprint to see exactly how it was made.

The `Class` object is that blueprint. Without it, you can't use Reflection. You must *enter* the Reflection API by obtaining this object first.

### The 3 Ways to Get the "ID Card" 🆔
Let's imagine we have a class named `User`. Here is how we get its "ID Card" (the Class object):

#### 1. 1. The "I already have the object" way
If you already have a `User` object in your hand, you can just ask it for its ID.

```java
User myUser = new User();
Class<?> clazz = myUser.getClass();
```

#### 2. The "I know the name at coding time" way
If you are writing code and you know you want to inspect the `User` class specifically, you can use the `.class` literal.

```java
Class<?> clazz = User.class;
```

#### 3. The "I only have a String" way (The most 'Reflective' way)
Imagine your program reads a text file that says `com.myapp.User`. You don't have the object, and you didn't hardcode the name. You only have a *String*.

```java
String className = "com.myapp.User";
Class<?> clazz = Class.forName(className);
```

> [!NOTE]
> **Why do we need this?**
> 
> Once you have this `clazz` object, you can start asking it **impossible** questions that normal Java can't answer at runtime:
> 
> - *Hey `clazz`, give me a list of all private variables inside you*.
> - *Hey `clazz`, create a new house (object) for me right now, even though I don't have the new keyword*.

## 🏗️ Accessing the "Guts" of a Class
In Reflection, we distinguish between two types of access:
1. **Public only:** Methods like `.getMethods()` or `.getFields()` return only the public members (including those inherited from parent classes).

2. **Everything (Declared):** Methods like `.getDeclaredMethods()` or `.getDeclaredFields()` return everything inside that specific class, including private and protected ones, but ignoring inherited ones.

To actually interact with a field or method, we use these two key classes:

- `java.lang.reflect.Field` Used to **read/write** data to a variable.

- `java.lang.reflect.Method` Used to **invoke** (call) a function.

**The "Secret" Step:** `setAccessible(true)`

By default, Java respects `private` modifiers. If you try to touch a private field via Reflection, you'll get an `IllegalAccessException`. To bypass this, you must call:

```java
myField.setAccessible(true);
```

---

## Playground
Create a new Java Project named `ReflectionAPIBasics` in your favourite IDE, for this we've used [Eclipse IDE](https://www.eclipse.org/downloads/packages/).

#### Step 1: The Target Class
Let's start with a very simple class. This is the **Target** we are going to inspect and manipulate.

- Inside your `ReflectionAPIBasics/src/` directory, create a package named `com.refapi.secret`.
- Create a new class named `SecretMessage` and copy this into the file in your Eclipse project.

```java
package com.refapi.secret;

public class SecretMessage {
    private String content = "Important: nobody should ever discover this message.";

    private void printSecret() {
        System.out.println("Secret Method says: " + content);
    }
}
```

> [!NOTE]
> Notice that everything is **private**. In a normal Java program, if you tried to use this class from another file, you couldn't see the string or run the method.

#### Step 2: Getting the "Blueprint" (`Class` object)
To use Reflection, we first need to get the **ID Card** or **Blueprint** of that class.

Start by creating a new file named `Main.java` inside the package `com.refapi`

```java
package com.refapi;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Get the Class object
        Class<?> clazz = SecretMessage.class;
        
        System.out.println("Class Name: " + clazz.getName());
    }
}
```

#### Step 3: Finding the Private Field
Now we want to find that `private String content`. We use `getDeclaredField()` method because it's *declared* inside the class (even if it's private).

```java
        // 2. Find the field by its name
        Field field = clazz.getDeclaredField("content");
```
> [!NOTE]
> Using `getDeclaredField("content")` is the only way to find that specific field because it's marked *private*.
> If you had used `getField()`, Java would have looked only for *public* fields and **thrown** a *not found error*.

> [!WARNING]
> **Wait!** If you try to run `field.get(new SecretMessage())` right now, Java will crash with an `IllegalAccessException`. 

#### Step 4: Breaking the Lock
Even after you find a private field, Java's security manager will block you from reading or writing to it. To bypass this, you use:

```java
		// 3. Unlock the field
        field.setAccessible(true);
```

#### Step 5: Reading the Value
Now that we've unlocked the field, we need to actually read it. Here is the tricky part: 

- The `Field` object we found (content) is just a definition. It's like having a map to a treasure chest, but you still need the actual chest to open it.

- In Reflection, we have to tell the field which specific instance of the class we want to look inside.

In our `Main.java`, let's create an **instance** of our secret class and pull the data out:

```java
        // 4. Create the actual "chest" (the object)
        SecretMessage myMessage = new SecretMessage();

		// 5. Tell the field: "Go into 'myMessage' and get the value"
		String capturedValue = (String) field.get(myMessage);

		System.out.println("Extracted String: " + capturedValue);
```

#### Step 6: Invoking the Private Method
Methods work almost the same way, but instead of `get()`, we use `invoke()`.

```java
		// 6. Find the method
		Method method = clazz.getDeclaredMethod("printSecret");

		// 7. Unlock the method as it is a private method
		method.setAccessible(true);

		// 8. Call the method or Run it!
		method.invoke(myMessage);
```

#### Let's Put It All Together
Here is the complete code for our `Main.java` file

```java
package com.refapi;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Get the Class object
        Class<?> clazz = SecretMessage.class;
        
        System.out.println("Class Name: " + clazz.getName());

        // 2. Find the field by its name
        Field field = clazz.getDeclaredField("content");

		// 3. Unlock the field
        field.setAccessible(true);

        // 4. Create the actual "chest" (the object)
        SecretMessage myMessage = new SecretMessage();

		// 5. Tell the field: "Go into 'myMessage' and get the value"
		String capturedValue = (String) field.get(myMessage);

		System.out.println("Extracted String: " + capturedValue);

		// 6. Find the method
		Method method = clazz.getDeclaredMethod("printSecret");

		// 7. Unlock the method as it is a private method
		method.setAccessible(true);

		// 8. Call the method or Run it!
		method.invoke(myMessage);
    }
}
```

---

## A Quick Challenge 🧠
What if our `printSecret` method required an argument, like a password?

**If the method took a `String` as an input, how do you think we tell `getDeclaredMethod` that we are looking for the version that takes a String?**

```java
private void printSecret(String password) { ... }
```

#### Solution
In Java, **Method Overloading** allows a class to have multiple methods with the same name but different *signatures* (different parameters).

If we just said `getDeclaredMethod("printSecret")`, the computer wouldn't know if we wanted:

- `printSecret()` (No arguments)

- `printSecret(String password)` (One String argument)

- `printSecret(int level, String key)` (An int and a String)

So, to pick the right one, we have to pass the **Class types** of the parameters as extra arguments. For a method that takes a `String`, we use `String.class`.

Here is how you can do it.

#### Step 1: Add `printSecret(String password)` method in `SecretMessage` class
Before accessing the method let's first quickly create an overloaded method for `printSecret`

```java
	// A private method with a parameter
	private void printSecret(String password) {
		if (!password.equals("admin@123")) // early exit
			return;

		System.out.println("Verified user! The secret is: " + content);
	}
```

#### Step 2: Invoke the parameterized method
Since we've added the required method, we can now `invoke` it using Reflection API

```java
		// Handle the private method
		// We tell it we want the version that takes a String
		Method method = clazz.getDeclaredMethod("printSecret", String.class);
		method.setAccessible(true);	// Unlock it

		// Run it! We pass the object AND the value for the parameter
		String password = "admin@123";
		method.invoke(myMessage, password);
```

> [!IMPORTANT]
> If you try to do this using modern Java (17+) to the **standard Java classes** (like `java.util.ArrayList`), it might block you with an `InaccessibleObjectException`.
> This is because Java now protects its own *internals* much more strictly. However, for our own classes like SecretMessage, it will work perfectly!

---

## Modifications and Object Creation! 🏗️
Let's get our hands dirty with some **illegal** modifications and object creation! 

#### 1. Changing a Private Value (Writing)
We've already used `field.get(obj)` to read a value. To change it, we use `field.set(obj, newValue)`. 
This is how frameworks like Hibernate *inject* data from a database directly into our private Java fields.

Here is how we'd change the secret message:

```java
// 1. Get and unlock the field (as we did before)
Field field = clazz.getDeclaredField("content");
field.setAccessible(true);

// 2. Overwrite the value! 
field.set(myMessage, "Thanks for writing it down so clearly.");

// 3. Verify it changed
System.out.println("New Value: " + field.get(myObject));
```

#### 2. Creating Objects without `new` 🧱
Usually, you use `new SecretMessage()`. But if you are a framework and only have the **String** `"SecretMessage"`, you can't type `new` because the compiler doesn't know that class exists yet.

In Reflection, we use the **Constructor** object.

1. **Get the Constructor:**
   
```java
Constructor<?> constructor = clazz.getDeclaredConstructor();
```

2. **Unlock it:**
Important if the constructor is private!

```java
constructor.setAccessible(true);
```

3. **Create the instance:**

```java
Object newObj = constructor.newInstance();
```

#### Let's Put It All Together
Here is the complete code for our `main` method.

```java
	public static void main(String[] args) throws Exception {
        Class<?> clazz = SecretMessage.class;

        // 1. Creating the object without 'new'
        Constructor<?> constructor = clazz.getDeclaredConstructor();

        // Even if the constructor was private, this would work:
        constructor.setAccessible(true); 

		// 2. Creating the instance
        Object myReflectedObject = constructor.newInstance();
        System.out.println("Object created via Reflection!");

        // 3. Changing the private field
        Field field = clazz.getDeclaredField("content");
        field.setAccessible(true);

        System.out.println("Before: " + field.get(myReflectedObject));
        
        // 4. Changing the value
        field.set(myReflectedObject, "Thanks for writing it down so clearly.");
        
        System.out.println("After: " + field.get(myReflectedObject));
    }
```

## 🛡️ Why Reflection is "Dangerous"
Now that you've seen how to bypass private, change values, and create objects out of thin air, you can see why this API is often called *The Swiss Army Knife* of Java. But with great power comes great responsibility.

Before we move into more complex aspects, let's look at the **three main risks** of using Reflection in a real project:

- **Performance:** Calling a method via Reflection is much slower than a direct call because the JVM has to "look up" the method and check security every single time.

- **Type Safety:** The compiler can't help you. If you typo a field name like `getDeclaredField("contnet")`, you won't find out until the program crashes at runtime.

- **Security:** You are essentially breaking the *Contract* of the class by touching private data, which can lead to unexpected behavior if the class wasn't designed for it.

## 🎓 Inspection
So far, we've been the "Puppet Master"—controlling the class. But Reflection is also great for Inspection

Imagine you have an object and you don't know anything about it. You can ask:

- **What interfaces do you implement?** (`clazz.getInterfaces()`)

- **What is your superclass?** (`clazz.getSuperclass()`)

- **Do you have any Annotations?** (`clazz.getAnnotations()`)

#### 🔍 The Inspection Toolkit
Here are the most common questions you can ask a `Class<?>` object:

| Method | What it tells you |
| :--- | :--- |
| `getSimpleName()` | The name of the class without the package (e.g., `"SecretMessage"`). |
| `getSuperclass()` | The parent class. |
| `getInterfaces()` | An array of all interfaces the class implements. |
| `isAnnotationPresent(Class<? extends Annotation> annotationClass)` | Checks if a specific tag (like `@Deprecated`) is on the class. |

---

## 🧪 Exercise: The "Class Explorer"
Try to write a small "Inspector" method `printSummary(Object obj)`. Imagine you want to create a tool that prints a summary of any object passed to it using Reflection API Only.

The function should print the following:
- Print the class name but should not include the package name in it.
- Print the parent class if exists, otherwise print "None".
- Tell if it's an interface or a regular class.

#### Solution

```java
public void printSummary(Object obj) {
    Class<?> clazz = obj.getClass();
    
    System.out.println("Class SimpleName: " + clazz.getSimpleName());
    
    Class<?> superClazz = clazz.getSuperclass();
	String parentClassName = (superClazz != null ? superClazz.getSimpleName() : "None");
    System.out.println("Parent class: " + parentClassName);

    System.out.println("Is it an interface? " + clazz.isInterface());
}
```

---

#### ⚓ We’ve successfully completed the basics of Reflection API