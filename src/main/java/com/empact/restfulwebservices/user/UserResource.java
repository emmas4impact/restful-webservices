package com.empact.restfulwebservices.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	//retreieve all users
	@GetMapping("/users")
	public List <User> retrieveAllUser(){
		return service.findAll();
		
	}
	
	//retreive user with id
	
	@GetMapping("/users/{id}")
	public EntityModel<User>  retrieveUserWithId(@PathVariable int id){
		User user =service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUser());
		resource.add(linkTo.withRel("all-users"));
		return resource;
		
	}
	
	//input - details of user
	//output - created & Return the created URI
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		java.net.URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User user =service.deleteById(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		
	}
	

}
