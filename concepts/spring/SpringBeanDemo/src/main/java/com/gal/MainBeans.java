package com.gal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gal.algo.Calc;
import com.gal.algo.PasswordHasher;
import com.gal.algo.UserVerifier;

public class MainBeans {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// which packages to scan for Annotations(like @Component)
		context.scan("com.gal");
		
		context.refresh();
		
		PasswordHasher bean = context.getBean(PasswordHasher.class);
		PasswordHasher bean2 = context.getBean(PasswordHasher.class);

		System.out.println(bean);
		System.out.println(bean2);

		System.out.println(bean.hash("Hello"));
		
		StringBuilder bean3 = context.getBean(StringBuilder.class);
		StringBuilder bean3a = context.getBean(StringBuilder.class);
		StringBuilder bean3b = context.getBean(StringBuilder.class);
		System.out.println(bean3);
		
		Calc bean4 = context.getBean(Calc.class);
		int sum = bean4.add(4, 5);
		System.out.println(sum);
		
		
		String[] beans = context.getBeanDefinitionNames();
		System.out.println("All Beans: [");
		for(String beanName: beans) {
			System.out.println("  " + beanName + ",");
		}
		System.out.println("]");
		
		
		Object bean5 = context.getBean("myCalc");
		Object bean6 = context.getBean("getCalc");
		
//		ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("beans.xml");
//		xmlContext.refresh();
//		
//		StringBuilder beanxml = xmlContext.getBean(StringBuilder.class);
//		System.out.println(beanxml);
		
		UserVerifier userVerifierBean = context.getBean(UserVerifier.class);
		userVerifierBean.verfiyUser();
		
		context.close();
	}
}
