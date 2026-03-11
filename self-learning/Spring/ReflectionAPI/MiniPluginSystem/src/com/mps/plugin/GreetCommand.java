package com.mps.plugin;

import com.mps.annotation.PluginInfo;
import com.mps.contract.Command;

// Normal-way
//public class GreetCommand implements Command {
//	public void execute() {
//		System.out.println("Hello from the Reflection Plugin!");
//	}
//}


// Using custom Annotation
@PluginInfo(name = "Friendly Greeter", author = "Devansh")
public class GreetCommand implements Command {
    public void execute() {
        System.out.println("Hello from the Reflection Plugin!");
    }
}