package com.qa.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.application.model.Login;
import com.qa.application.repository.LoginRepo;

@Service
public class LoginService {
	
	private LoginRepo repo;

	public LoginService(LoginRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Login create(Login login) {
		return this.repo.saveAndFlush(login);
	}
	
	public List<Login> getAll() {
		return this.repo.findAll();
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public Login getById(Long id) {
		return this.repo.findById(id).get();
	}
	
	public Login update(Long id , Login login) {
		Login exist = this.repo.findById(id).get();
		exist.setUsername(login.getUsername());
		exist.setPassword(login.getPassword());
		return this.repo.saveAndFlush(exist);
	}

}
