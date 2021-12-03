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

import com.qa.application.model.Country;
import com.qa.application.repository.CountryRepo;

@SpringBootTest
public class CountryServiceTests {
	
	@Autowired
	private CountryService service;
	
	@MockBean
	private CountryRepo repo;
	
	@Test
	void testCreate() {
		final Country INPUT = new Country("egypt", "22/01/2022", 3L);
		final Country OUTPUT = new Country(1L, "egypt", "22/01/2022", 3L);
		
		Mockito.when(this.repo.saveAndFlush(INPUT)).thenReturn(OUTPUT);
		Assertions.assertThat(this.service.create(INPUT)).isEqualTo(OUTPUT);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(INPUT);
	}
	
	@Test
	void testGetAll() {
		List<Country> newList = new ArrayList<>();
		final Country SAVED_COUNTRY = new Country(1L, "belgium", "10/12/2022", 3L);
		newList.add(SAVED_COUNTRY);
		
		Mockito.when(this.repo.findAll()).thenReturn(newList);
		Assertions.assertThat(this.service.getAll()).isEqualTo(newList);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	 
	
	@Test  
	public void testGet() {
	    final Country SAVED_COUNTRY = new Country(17L, "france", "05/04/2016", 10L);

	    Mockito.when(this.repo.findById(17L)).thenReturn(Optional.of(SAVED_COUNTRY));
	    Assertions.assertThat(this.service.get(SAVED_COUNTRY.getId())).isEqualTo(SAVED_COUNTRY);
	    Mockito.verify(this.repo, Mockito.times(1)).findById(SAVED_COUNTRY.getId());
	  }
	 
	 
	 @Test 
	 void testUpdate() {
		final Country OLD_COUNTRY = new Country(17L, "france", "05/04/2016", 10L);
	    final Country NEW_COUNTRY = new Country("france", "05/04/2016", 10L);
	    final Country NEW_COUNTRY_ID = new Country(17L, "france", "05/04/2016", 10L);

	    Mockito.when(this.repo.findById(17L)).thenReturn(Optional.of(OLD_COUNTRY));
	    Mockito.when(this.repo.saveAndFlush(NEW_COUNTRY)).thenReturn(NEW_COUNTRY_ID);

	    Country returnedCountry = service.update(17L, NEW_COUNTRY);
	    Assertions.assertThat(returnedCountry = NEW_COUNTRY_ID);
	  
	    Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(NEW_COUNTRY_ID);
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
