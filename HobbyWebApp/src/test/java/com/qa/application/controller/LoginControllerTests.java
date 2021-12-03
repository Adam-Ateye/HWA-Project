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
import com.qa.application.model.Login;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/sql-schema.sql", "classpath:/sql-data.sql"},
		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class LoginControllerTests {
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void createTest() throws Exception {
		Login entry = new Login("john", "red123");
		Login result = new Login(2L, "john", "red123");
		
		String entryAsJson = this.mapper.writeValueAsString(entry);
		String resultAsJson = this.mapper.writeValueAsString(result);
		
		RequestBuilder request = post("/login/create").contentType(MediaType.APPLICATION_JSON).
				content(entryAsJson);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(resultAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGet() throws Exception {
		Login jack = new Login(1L, "jack", "lion1234");
		String jackAsJson = this.mapper.writeValueAsString(jack);
		RequestBuilder request = get("/login/get/1");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(jackAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		Login jack = new Login(1L, "jack", "lion1234");
		String loginJson = this.mapper.writeValueAsString(List.of(jack));
		RequestBuilder request = get("/login/getAll");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(loginJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	
	@Test
	void testUpdate() throws Exception {
		Login jack = new Login("jack", "lion1234");
		String jackAsJson = this.mapper.writeValueAsString(jack);
		RequestBuilder request = put("/login/update").contentType(MediaType.APPLICATION_JSON)
				.content(jackAsJson);
		
		ResultMatcher checkStatus = status().isAccepted();
		
		Login jackSaved = new Login(1L, "jack", "lion1234");
		String jackSavedAsJson = this.mapper.writeValueAsString(jackSaved);
		
		ResultMatcher checkBody = content().json(jackSavedAsJson);
		
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/login/delete/1")).andExpect(status().isNoContent());
	}

}
