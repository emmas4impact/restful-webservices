package com.empact.restfulwebservices.user;

import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//retreieve all users
	@GetMapping("jpa/users")
	public List <User> retrieveAllUser(){
		return userRepository.findAll();
		
	}
	
	//retreive user with id
	
	@GetMapping("jpa/users/{id}")
	public EntityModel<Optional<User>>  retrieveUserWithId(@PathVariable int id){
		Optional<User> user =userRepository.findById(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		EntityModel<Optional<User>> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUser());
		resource.add(linkTo.withRel("all-users"));
		return resource;
		
	}
	
	//input - details of user
	//output - created & Return the created URI
	
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		java.net.URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
				
	}
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> retrieveAllUser(@PathVariable int id){
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+ id);
		}
		 return userOptional.get().getPost();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		//User savedPost = userRepository.findById(id)
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+ id);
		}
		User user = userOptional.get();
		post.setUser(user);
		
		@Valid Post savedPost = postRepository.save(post);
		
		java.net.URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedPost.getId())
		.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	

}
