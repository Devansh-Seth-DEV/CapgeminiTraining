package com.refapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.refapi.exercise.ClassExplorerExercise;
import com.refapi.secret.SecretMessage;

public class Main {
	public static void main(String[] args) throws Exception {
		accessSecretMessageClassPrivateField();
		accessSecretMessageClassPrivateMethod();
		accessSecretMessageClassPrivateMethodWithParam();
		setSecretMessageClassPrivateField();
		constructSecretMessageClassObject();
		exeClassExplorerExercise();
	}
	
	
	public static void accessSecretMessageClassPrivateField() throws Exception {
		// Get the class Object
		Class<?> secretMessageClass = SecretMessage.class;
		
		System.out.println("Class Name: " + secretMessageClass);
		
		// Find the field by its name
        Field field = secretMessageClass.getDeclaredField("content");

        // Tells the JVM to ignore the private modifier just for this specific operation.
        field.setAccessible(true);

        // Create the actual object
        SecretMessage myMessage = new SecretMessage();

        // Tell the field: "Go into 'myMessage' and get the value"
        String capturedValue = (String) field.get(myMessage);
        System.out.println("Extracted String: " + capturedValue);
	}

	public static void accessSecretMessageClassPrivateMethod() throws Exception {
		// Get the class Object
		Class<?> secretMessageClass = SecretMessage.class;
		
		System.out.println("Class Name: " + secretMessageClass);

		// Find the method its name
		Method method = secretMessageClass.getDeclaredMethod("printSecret");
		method.setAccessible(true);

        // Create the actual object
        SecretMessage myMessage = new SecretMessage();

        // Tell the method: "Go into 'myMessage' and invoke the method of this instance."
        method.invoke(myMessage);
	}
	
	public static void accessSecretMessageClassPrivateMethodWithParam() throws Exception {
		// Get the class Object
		Class<?> secretMessageClass = SecretMessage.class;
		
		System.out.println("Class Name: " + secretMessageClass);

		// Find the method its name
		Method method = secretMessageClass.getDeclaredMethod("printSecret",
															 String.class);
		method.setAccessible(true);	// unlock it

        // Create the actual object
        SecretMessage myMessage = new SecretMessage();

        // Tell the method: "Go into 'myMessage' and invoke the method of this instance."
        method.invoke(myMessage, "Devansh");
	}
	
	public static void setSecretMessageClassPrivateField() throws Exception {
				// Get the class Object
		Class<?> secretMessageClass = SecretMessage.class;
		
		System.out.println("Class Name: " + secretMessageClass);
		
		// Find the field by its name
        Field field = secretMessageClass.getDeclaredField("content");

        // Tells the JVM to ignore the private modifier just for this specific operation.
        field.setAccessible(true);

        // Create the actual object
        SecretMessage myMessage = new SecretMessage();

        // Overwrite the value! 
        field.set(myMessage, "The eagle has departed.");

        // Tell the field: "Go into 'myMessage' and get the value"
        String capturedValue = (String) field.get(myMessage);
        System.out.println("Extracted String: " + capturedValue);
	}
	
	public static void constructSecretMessageClassObject() throws Exception {
		Class<?> secretMessageClass = SecretMessage.class;
		
		// Get the constructor
		Constructor<?> secretMessageClassConstructor = secretMessageClass.getDeclaredConstructor();

		// Even if the constructor was private, this would work
		secretMessageClassConstructor.setAccessible(true);
		
		// Create instance
		Object myReflectedObject = secretMessageClassConstructor.newInstance();
		System.out.println("Object created via Reflection!");
		
		// Changing the private field
        Field field = secretMessageClass.getDeclaredField("content");
        field.setAccessible(true);

        System.out.println("Before: " + field.get(myReflectedObject));
        
        // Changing the value
        field.set(myReflectedObject, "Updated via Reflection!");
        
        System.out.println("After: " + field.get(myReflectedObject));
	}
	
	public static void exeClassExplorerExercise() {
		SecretMessage myMessage = new SecretMessage();
		ClassExplorerExercise exercise = new ClassExplorerExercise();
		exercise.printSummary(myMessage);
	}
}
