package com.ilp.rest.webservices.restfulwebservices.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="https://jeremtarenio.github.io/recipe-book") //tells spring to grant access to the url in accessing this api
@RestController
public class AuthenticationController {

	@GetMapping(path = "/basicauth")
	public AuthenticationBean getAuthenticationBean() {
		return new AuthenticationBean("Authenticated.");
	}
	
}
