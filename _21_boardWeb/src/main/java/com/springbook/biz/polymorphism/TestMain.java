package com.springbook.biz.polymorphism;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		GenericXmlApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		TV bean = factory.getBean("tv", TV.class);
		bean.powerOn();
		bean.volumeUp();
		
		factory.close();
	}
}
