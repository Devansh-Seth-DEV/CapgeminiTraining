# Mini Plugin System 🛠️

In a real plugin system, the main application doesn't know what commands exist until it "scans" for them. We'll simulate this by creating a simple interface that all plugins must follow.

### The Plan 🗺️
1. **Define the Contract:** Create an interface called `Command`.
2. **Create Plugins:** Write two simple classes (like `GreetCommand` and `TimeCommand`) that implement that interface.
3. **The `Scanner`:** Use ***Reflection*** to load and run these classes based only on their names as Strings.

### 🧩 Pre-requisites
Before making this Project first understand the basics of Reflection API.

<a href="https://github.com/Devansh-Seth-DEV/CapgeminiTraining/tree/main/self-learning/Spring/ReflectionAPI/ReflectionAPIBasics">
	<img src="https://img.shields.io/badge/Learn_Reflection_API_Basics-181717?style=plastic&logo=github&logoColor=white&color=purple" alt="Explore Repo">
</a>


### Step 1: The Contract 📜
Create a new Java Project named `MiniPluginSystem` in your favourite IDE, for this we're using [Eclipse IDE](https://www.eclipse.org/downloads/packages/).

Let's define what a "Command" looks like. Create this interface in your Eclipse project inside the package named `com.mps.contract`:

```java
package com.mps.contract;

public interface Command {
	void execute();
}
```

### Step 2: The Plugins 🔌
Now, let's create two classes inside the package named `com.mps.plugin`.

#### GreetCommand.java

```java
package com.mps.plugin;

public class GreetCommand implements Command {
    public void execute() {
        System.out.println("Hello from the Reflection Plugin!");
    }
}
```

#### TimeCommand.java

```java
package com.mps.plugin;

public class TimeCommand implements Command {
    public void execute() {
        System.out.println("Current time: " + java.time.LocalTime.now());
    }
}
```

### Step 3: The Reflection "Engine" ⚙️
Now for the magic. We want a program that asks the user for a command name and runs it—without using a `switch` or `if/else` block for every possible command.

Here is the logic we need:
1. Take a **String** (the class name).
2. Use `Class.forName()` to find the blueprint.
3. Create an instance using the **Constructor**.
4. Cast it to our `Command` interface so we can call `execute()`.

Now, let's build the **Reflective Engine** that brings the Plugin System to life. We'll write a main method that can load any class that implements our `Command` interface.

- Create a `Main.java` file inside `com.mps` package.
- Copy this into your Main.java file.

```java
package com.mps;

public class Main {
	private static String[] plugins = {
		"GreetCommand",
		"TimeCommand"
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Available Plugins:");
		for(int i=1; i <= plugins.length; i++) {
			System.out.println("[%s] - %s".formatted(i, plugins[i-1]));
		}
		
		System.out.print("\nEnter the plugin name to run: ");
		String pluginClassName = sc.next();
		String pluginPackage = "com.mps.plugin";
		String absPluginClassName = pluginPackage + "." + pluginClassName;
		
		System.out.println("Plugin selected: " + pluginClassName);
		
		try {
			// Give the full class-path (e.g com.mps.plugin.GreetCommand)
			Class<?> clazz = Class.forName(absPluginClassName);
			Constructor<?> constructor = clazz.getDeclaredConstructor();

			constructor.setAccessible(true); // unlock the private fields
			Object rawObject = constructor.newInstance();
			
			if (rawObject instanceof Command) {
				Command command = (Command) rawObject;
				command.execute();	// Run the plugin!
			} else {
				System.out.println("Error: class doesnot implement Command interface.");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Plugin not found: " + pluginClassName);
        } catch (Exception e) {
            // This catches NoSuchMethodException, InstantiationException, etc.
            e.printStackTrace();
        }

		sc.close();
	}
}
```

---

## 🏷️ The Power of Annotations
Another way to make our system smarter is using **Annotations**. We could mark specific classes with a custom label like `@PluginInfo`.

Annotations allow you to attach **metadata** (extra information) to our classes, methods, or fields. Reflection then acts as the **reader** that looks for these tags and decides what to do based on them.

> [!TIP]
> 
> Using Reflection, we can ask the class
> 
> Do you have the `@PluginInfo` annotation? If so, what is your `name` property?
> 
> This is how frameworks like Spring know which classes to turn into Beans without you telling them manually.

In Java, you define an annotation using the `@interface` keyword. We also use two **Meta-Annotations** to tell Java how to handle our tag:

- `@Retention(RetentionPolicy.RUNTIME)`: This is crucial! It tells Java to keep the annotation alive even after the code is compiled, so our Reflection code can see it while the app is running.

- `@Target`: Defines where the tag can be placed (e.g., on a Class, a Method, or a Field).

### 1. Adding Metadata to Plugins
Let's enhance our Mini Plugin System. We’ll create an annotation called `@PluginInfo` so each command can describe itself (like its name and who wrote it). For this first Create a `PluginInfo.java` file inside `com.mps.annotations` package.

#### PluginInfo.java

```java
package com.mps.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // TYPE means this is for Classes/Interfaces
public @interface PluginInfo {
    String name();
    String author() default "Anonymous";
}
```

#### Updated GreetCommand.java

```java
package com.mps.plugin;

@PluginInfo(name = "Friendly Greeter", author = "MPS")
public class GreetCommand implements Command {
    public void execute() {
        System.out.println("Hello from the Reflection Plugin!");
    }
}
```

### 2. Reading the Tag with Reflection 🔍
Now, in our `Main` class, after you get the `clazz` object, you can *inspect* it for this specific tag.

```java
// Check if the class has our special label
if (clazz.isAnnotationPresent(PluginInfo.class)) {
    // Grab the actual annotation object
    PluginInfo info = clazz.getAnnotation(PluginInfo.class);
    
    // Access the data inside the tag!
    System.out.println("Plugin Name: " + info.name());
    System.out.println("Author: " + info.author());
}
```

---

## 🧪 Putting it into Practice

```java
package com.mps;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Available Plugins:");
		for(int i=1; i<=allPluginClassNames.length; i++) {
			System.out.println("[%s] - %s".formatted(i, allPluginClassNames[i-1]));
		}
		
		System.out.print("\nEnter the plugin name to run: ");
		String pluginClassName = sc.next();
		String pluginPackage = "com.mps.plugin";
		String absPluginClassName = pluginPackage + "." + pluginClassName;
		
		System.out.println("Plugin selected: " + pluginClassName);
		
		runPlugin(absPluginClassName);

		sc.close();
	}

	private static void runPlugin(String pluginClassName) {
		try {
			Class<?> clazz = Class.forName(pluginClassName);
			
			if (clazz.isAnnotationPresent(PluginInfo.class)) {
				// Grab the annotation object
				PluginInfo info = clazz.getAnnotation(PluginInfo.class);
				
				// Access the data inside tag!
				System.out.println("Plugin name: " + info.name());
				System.out.println("Author: " + info.author());
			}
			
			Constructor<?> constructor = clazz.getDeclaredConstructor();

			constructor.setAccessible(true); // unlock the private fields
			Object rawObject = constructor.newInstance();
			
			if (rawObject instanceof Command) {
				Command command = (Command) rawObject;
				command.execute();	// Run the plugin!
			} else {
				System.out.println("Error: class doesnot implement Command interface.");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Plugin not found: " + pluginClassName);
        } catch (Exception e) {
            // This catches NoSuchMethodException, InstantiationException, etc.
            e.printStackTrace();
        }	
	}
	
	private static String[] allPluginClassNames = {
		"GreetCommand",
		"TimeCommand"
	};
}
```

---
