package com.mps.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// It tells Java to keep the annotation alive even after the code is compiled, 
// so our Reflection code can see it while the app is running.
@Retention(RetentionPolicy.RUNTIME)

// Defines where the tag can be placed (e.g., on a Class, a Method, or a Field).
// TYPE means this is for Classes/Interfaces
@Target(ElementType.TYPE)
public @interface PluginInfo {
	String name();

	// By adding default, we make this field optional
	String author() default "Anonymous";
}
