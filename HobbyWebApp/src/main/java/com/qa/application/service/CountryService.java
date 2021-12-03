package com.qa.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.qa.application.model.Country;
import com.qa.application.repository.CountryRepo;

@Service
public class CountryService {
	
	private CountryRepo repo;
	
	public CountryService(CountryRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Country create(Country country) {
		return this.repo.saveAndFlush(country);
	}
	
	public List<Country> getAll() {
		return this.repo.findAll();
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	
	public Country get(Long Id) {
		return this.repo.findById(Id).get();
	}
	
	public Country update(Long id, Country country) {
		Country exist = this.repo.findById(id).get();
		exist.setId(country.getId());
		exist.setName(country.getName());
		exist.setDate(country.getDate());
		exist.setLength(country.getLength());
		this.repo.saveAndFlush(exist);
		return exist;
	}
	
	
	
}
