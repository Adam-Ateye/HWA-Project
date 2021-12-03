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

import com.qa.application.model.Country;
import com.qa.application.service.CountryService;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService service;

	public CountryController(CountryService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Country> create(@RequestBody Country country) {
		return new ResponseEntity<Country>(this.service.create(country),HttpStatus.CREATED);
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<Country>> getAll(){
		return new ResponseEntity<List<Country>>(this.service.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Country> get(@PathVariable Long id, @RequestBody Country country) {
		return new ResponseEntity<Country>(this.service.get(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Country> delete(@PathVariable Long id) {
		
		return this.service.delete(id) ? new ResponseEntity<Country>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<Country>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody Country country) {
		return new ResponseEntity<Country>(this.service.update(id, country),HttpStatus.ACCEPTED);
	}
	
	
	
}
