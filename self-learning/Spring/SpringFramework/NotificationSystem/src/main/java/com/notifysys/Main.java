package com.notifysys;

import com.notifysys.config.AppConfig;
import com.notifysys.model.UserAlert;
import com.notifysys.service.MessageService;
import com.notifysys.service.OnlineMessageService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
	    testBeans();
	    testScopePrototype();
	    testXmlBeans();
	}

	private static void testXmlBeans() {
	    ClassPathXmlApplicationContext context =
	            new ClassPathXmlApplicationContext("beans.xml");

	    OnlineMessageService service =
	            context.getBean(OnlineMessageService.class);

	    service.send("Spring is officially running using XML Beans!");

	    context.close();
	}

	private static void testScopePrototype() {
	    AnnotationConfigApplicationContext context = new
	        AnnotationConfigApplicationContext(AppConfig.class);

	    // First alert
	    UserAlert alert1 = context.getBean(UserAlert.class);
	    alert1.setMessage("You have a new friend request!");

	    // Second alert
	    UserAlert alert2 = context.getBean(UserAlert.class);
	    alert2.setMessage("Your battery is low!");

	    // Display both alerts
	    alert1.show();
	    alert2.show();

	    context.close();
	}

	private static void testBeans() {
	    // Startup the Context using our Config class
	    AnnotationConfigApplicationContext context = new
	        AnnotationConfigApplicationContext(AppConfig.class);

	    // Ask the Context for the Bean (The object Spring created for us)
	    MessageService service = context.getBean(MessageService.class);

	    // Use it!
	    service.send("Spring is officially running!");

	    context.close();
	}
}