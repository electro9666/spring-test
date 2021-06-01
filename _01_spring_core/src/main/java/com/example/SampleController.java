package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample")
@RestController()
public class SampleController {

//	@Autowired
//	ApplicationContext applicationContext;

	@GetMapping(path = "/a")
	public Object p1() {
//		System.out.println("applicationContext:" + applicationContext);
//		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//		for (String string : beanDefinitionNames) {
//			System.out.println(string + " : " + applicationContext.getBean(string));
//		}
		return "A";
	}
}
