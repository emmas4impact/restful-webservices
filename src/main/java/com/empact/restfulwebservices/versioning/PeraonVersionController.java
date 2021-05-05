package com.empact.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeraonVersionController {
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Emmanuel Adedeji");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Emmanuel", "Adedeji"));
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Emmanuel Adedeji");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Emmanuel", "Adedeji"));
	}
	
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 produceV1() {
		return new PersonV1("Emmanuel Adedeji");
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 produceV2() {
		return new PersonV2(new Name("Emmanuel", "Adedeji"));
	}


}
