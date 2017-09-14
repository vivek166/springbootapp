package com.synerzip.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.synerzip.app.model.User;
import com.synerzip.app.service.UserService;


@RestController
@RequestMapping(path="/api/user")
public class UserController {

	@Autowired
	UserService service;
	
	@RequestMapping(value= "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> gets(){
		return service.getUsers();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User get(@PathVariable int id){
		return service.getUser(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public String add(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String update(@RequestBody User user, @PathVariable int id) {
		return service.updateUser(id, user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable int id) {
		service.deleteUser(id);
		return "user deleted";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "email" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getByEmail(@RequestParam("email") String email) {
		return service.getByEmail(email);
	}
	
	@RequestMapping(value="/mapreduce", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String map(){
		return service.doMapReduce();
	}
}
