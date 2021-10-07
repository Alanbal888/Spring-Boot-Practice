package com.alan.in28minutes.rest.webservices.restfulwebservices.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.alan.in28minutes.rest.webservices.restfulwebservices.beans.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "<h1>Hello World!</h1>";
	}

	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hey man");
	}

	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("Your parameter:" + name);
	}
	
	
	@GetMapping("/hello-world/internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader("Accept-Language") Locale locale
			) {
		Locale locale = LocaleContextHolder.getLocale();
		String greeting = messageSource.getMessage("good.morning.message", null, "Default message", locale);
		return greeting;
	}

}
 