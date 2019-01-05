package com.rbailen.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	//localhost:8085/v1/person
	@GetMapping("v1/person")
	public PersonV1 personV1(){
		return new PersonV1("Cristiano Ronaldo");
	}
	
	//localhost:8085/v2/person
	@GetMapping("v2/person")
	public PersonV2 personV2(){
		return new PersonV2(new Name("Cristiano", "Ronaldo"));
	}
	
	//localhost:8085/person/param?version=1
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Cristiano Ronaldo");
	}

	//localhost:8085/person/param?version=2
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Cristiano", "Ronaldo"));
	}

	//localhost:8085/person/header (KEY: X-API-VERSION, Value: 1)
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Cristiano Ronaldo");
	}

	//localhost:8085/person/header (KEY: X-API-VERSION, Value: 2)
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Cristiano", "Ronaldo"));
	}

	//localhost:8085/person/produces (KEY: Accept, Value: application/vnd.company.app-v1+json)
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Cristiano Ronaldo");
	}

	//localhost:8085/person/produces (KEY: Accept, Value: application/vnd.company.app-v2+json)
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Cristiano", "Ronaldo"));
	}
	
}
