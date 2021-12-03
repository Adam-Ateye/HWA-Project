package com.qa.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.application.model.Country;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/sql-schema.sql", "classpath:/sql-data.sql"},
		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CountryControllerTests {
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void createTest() throws Exception {
		Country entry = new Country("Germany", "17/08/2022", 10L);
		Country result = new Country(2L, "Germany", "17/08/2022", 10L);
		
		String entryAsJson = this.mapper.writeValueAsString(entry);
		String resultAsJson = this.mapper.writeValueAsString(result);
		
		RequestBuilder request = post("/country/create").contentType(MediaType.APPLICATION_JSON).
				content(entryAsJson);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(resultAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGet() throws Exception {
		Country usa = new Country(1L, "usa", "19/02/2024", 10L);
		String usaAsJson = this.mapper.writeValueAsString(usa);
		RequestBuilder request = get("/country/get/1");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(usaAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		Country usa = new Country(1L, "usa", "19/02/2024", 10L);
		String countryJson = this.mapper.writeValueAsString(List.of(usa));
		RequestBuilder request = get("/country/getAll");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(countryJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	void testUpdate() throws Exception {
		Country uae = new Country("uae", "24/07/2024", 10L);
		String uaeAsJson = this.mapper.writeValueAsString(uae);
		RequestBuilder request = put("/country/update").contentType(MediaType.APPLICATION_JSON)
				.content(uaeAsJson);
		
		ResultMatcher checkStatus = status().isAccepted();
		
		Country uaeSaved = new Country(1L, "uae", "24/07/2024", 10L);
		String uaeSavedAsJson = this.mapper.writeValueAsString(uaeSaved);
		
		ResultMatcher checkBody = content().json(uaeSavedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/country/delete/1")).andExpect(status().isNoContent());
	}
	
	
	
	
	
	
	
	
}
