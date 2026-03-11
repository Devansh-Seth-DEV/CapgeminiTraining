package com.mps;

import java.lang.reflect.Constructor;
import java.util.Scanner;

import com.mps.annotation.PluginInfo;
import com.mps.contract.Command;

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
