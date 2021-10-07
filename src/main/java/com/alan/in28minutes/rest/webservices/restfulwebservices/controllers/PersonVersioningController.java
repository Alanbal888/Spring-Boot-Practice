package com.alan.in28minutes.rest.webservices.restfulwebservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alan.in28minutes.rest.webservices.restfulwebservices.models.Name;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.PersonV1;
import com.alan.in28minutes.rest.webservices.restfulwebservices.models.PersonV2;

@RestController
@RequestMapping("person")
public class PersonVersioningController {

	/*
	 * Basic URI Versioning
	 * */
	
	@GetMapping("/v1")
	public PersonV1 personV1(){
		return new PersonV1("Alan Balderas");
	}
	
	@GetMapping("/v2")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Alan", "Balderas"));
	}
	
	/*
	 * Parameter versioning
	 * */
	@GetMapping(value="/params", params="version=1")
	public PersonV1 versioningWithParamsV1() {
		return new PersonV1("Alan Balderas");
	}
	
	
	@GetMapping(value="/params", params="version=2")
	public PersonV2 versioningWithParamsV2() {
		return new PersonV2(new Name("Alan", "Balderas"));
	}
	
	
	/*
	 * Headers versioning
	 * */
	@GetMapping(value="/headers", headers="X-API-VERSION=1")
	public PersonV1 versioningWithHeadersV1() {
		return new PersonV1("Alan Balderas");
	}
	
	
	@GetMapping(value="/headers", headers="X-API-VERSION=2")
	public PersonV2 versioningWithHeadersV2() {
		return new PersonV2(new Name("Alan", "Balderas"));
	}
	
	
	/*
	 * Content Negotiation
	 * */
	@GetMapping(value="/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 versioningWithContentNegotiationV1() {
		return new PersonV1("Alan Balderas");
	}
	
	
	@GetMapping(value="/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 versioningWithContentNegotiationV2() {
		return new PersonV2(new Name("Alan", "Balderas"));
	}
	
}
