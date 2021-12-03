package com.qa.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.application.model.Login;
import com.qa.application.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService service;

	public LoginController(LoginService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Login> create(@RequestBody Login login) {
		return new ResponseEntity<Login>(this.service.create(login),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Login>> getAll() {
		return new ResponseEntity<List<Login>>(this.service.getAll(),HttpStatus.OK);
				
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Login> update(@PathVariable Long id, @RequestBody Login login) {
		return new ResponseEntity<Login>(this.service.update(id, login),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Login> getById(@PathVariable Long id) {
		return new ResponseEntity<Login>(this.service.getById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Login> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<Login>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Login>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
