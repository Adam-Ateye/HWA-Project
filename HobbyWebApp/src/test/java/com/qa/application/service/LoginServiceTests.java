package com.qa.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.application.model.Login;
import com.qa.application.repository.LoginRepo;

@SpringBootTest
public class LoginServiceTests {
	
	@Autowired
	private LoginService service;
	
	@MockBean
	private LoginRepo repo;
	
	@Test
	void testCreate() {
		final Login INPUT = new Login("john", "red123");
		final Login OUTPUT = new Login(1L, "john", "red123");
		
		Mockito.when(this.repo.saveAndFlush(INPUT)).thenReturn(OUTPUT);
		Assertions.assertThat(this.service.create(INPUT)).isEqualTo(OUTPUT);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(INPUT);
	}
	
	@Test
	void testGetAll() {
		List<Login> newList = new ArrayList<>();
		final Login SAVED_LOGIN = new Login(1L, "dave", "password2");
		newList.add(SAVED_LOGIN);
		
		Mockito.when(this.repo.findAll()).thenReturn(newList);
		Assertions.assertThat(this.service.getAll()).isEqualTo(newList);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	 
	
	@Test  
	public void testGet() {
	    final Login SAVED_LOGIN = new Login(21L, "adam", "password1");

	    Mockito.when(this.repo.findById(21L)).thenReturn(Optional.of(SAVED_LOGIN));
	    Assertions.assertThat(this.service.getById(SAVED_LOGIN.getId())).isEqualTo(SAVED_LOGIN);
	    Mockito.verify(this.repo, Mockito.times(1)).findById(SAVED_LOGIN.getId());
	  }
	 
	 
	 @Test 
	 void testUpdate() {
		final Login OLD_LOGIN = new Login(21L, "adam", "password1");
	    final Login NEW_LOGIN = new Login("dave", "password2");
	    final Login NEW_LOGIN_ID = new Login(21L, "dave", "password2");

	    Mockito.when(this.repo.findById(21L)).thenReturn(Optional.of(OLD_LOGIN));
	    Mockito.when(this.repo.saveAndFlush(NEW_LOGIN)).thenReturn(NEW_LOGIN_ID);

	    Login returnedLogin = service.update(21L, NEW_LOGIN);
	    Assertions.assertThat(returnedLogin = NEW_LOGIN_ID);
	  
	    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(NEW_LOGIN_ID);
	 }
	  
	 @Test
	 void testDelete() {
		 Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		 Assertions.assertThat(this.service.delete(1L)).isEqualTo(true);
		 Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		 Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	 }
	 
	 @Test
	 void testDeleteFail() {
		 Mockito.when(this.repo.existsById(2L)).thenReturn(true);
		 Assertions.assertThat(this.service.delete(2L)).isEqualTo(false);
		 Mockito.verify(this.repo, Mockito.times(1)).deleteById(2L);
		 Mockito.verify(this.repo, Mockito.times(1)).existsById(2L);
	 }

}
