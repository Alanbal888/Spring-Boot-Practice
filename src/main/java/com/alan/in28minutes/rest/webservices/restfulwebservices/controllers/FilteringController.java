package com.alan.in28minutes.rest.webservices.restfulwebservices.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alan.in28minutes.rest.webservices.restfulwebservices.beans.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("filtering")
public class FilteringController {

	
	@GetMapping
	public MappingJacksonValue getFilteredBean() {
		
		
		SomeBean someBean = new SomeBean("A value 1", "A value 2", "A value 3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
	@GetMapping("/list")
	public MappingJacksonValue getFilteredBeans() {
		
	
		List<SomeBean> beans = Arrays.asList(new SomeBean("A value 1", "A value 2", "A value 3"), new SomeBean("A value 11", "A value 22", "A value 33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(beans);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
}
