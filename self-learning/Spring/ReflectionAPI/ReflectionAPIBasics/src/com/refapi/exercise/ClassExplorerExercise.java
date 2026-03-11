package com.refapi.exercise;

public class ClassExplorerExercise {
	public void printSummary(Object obj) {
	    Class<?> clazz = obj.getClass();
	    
	    System.out.println("Exploring class: " + clazz.getSimpleName());
	    
	    // How would we check the parent class?
	    Class<?> superClazz = clazz.getSuperclass();
	    System.out.println("Parent class: " + (superClazz != null ? superClazz.getSimpleName() : "None"));

	    // Let's see if it's an interface or a regular class
	    System.out.println("Is it an interface? " + clazz.isInterface());
	}
}
