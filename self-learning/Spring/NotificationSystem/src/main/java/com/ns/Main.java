package com.ns;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ns.config.ProjectConfig;
import com.ns.model.UserAlert;
import com.ns.service.MessageService;

public class Main {
    public static void main(String[] args) {
        // 1. Startup the Context using our Config class
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    	
    	// Loading from XML instead of the @Configuration class
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 2. Ask the Context for the Bean (The object Spring created for us)
        MessageService service = context.getBean(MessageService.class);

        // 3. Use it!
        service.send("Spring is officially running!");
        
        
        // Using scope = prototype
        // 1. Get the first alert and set a message
//        UserAlert alert1 = context.getBean(UserAlert.class);
//        alert1.setMessage("You have a new friend request!");
        
        // 2. Get a second alert
//        UserAlert alert2 = context.getBean(UserAlert.class);
//        alert2.setMessage("Your battery is low!");
        
        // 3. Show both of them
//        alert1.show();
//        alert2.show();

        context.close();
    }
}