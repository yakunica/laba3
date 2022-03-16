package com.anton.demo;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetItems() throws Exception {
		mockMvc.perform(get("/items"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetUsers() throws Exception {
		mockMvc.perform(get("/users"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetSuppliers() throws Exception {
		mockMvc.perform(get("/suppliers"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetNews() throws Exception {
		mockMvc.perform(get("/news"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetSuppliersItems() throws Exception {
		mockMvc.perform(get("/suppliersitems"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void signIn() throws Exception {
		mockMvc.perform(post("/signin").content(" {\n" +
								"    \"login\": \"yakunica\",\n" +
								"    \"password\": \"pas\"\n" +
								"}").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void addRequest() throws Exception {
		mockMvc.perform(post("/request").content(" {\n" +
						"    \"count\": 270,\n" +
						"    \"status\": 3,\n" +
						"    \"item\": {\n" +
						"    \"id\": 4,\n" +
						"    \"name\": \"Нефть\",\n" +
						"    \"unit\": \"Баррель\",\n" +
						"    \"count\": 0\n" +
						"    }\n" +
						"}").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void addUser() throws Exception {
		mockMvc.perform(post("/signup").content(" {\n" +
						"    \"name\": \"Степанов Арсений Артёмович\",\n" +
						"    \"profession\": \"Программист\",\n" +
						"    \"post\": null,\n" +
						"    \"dateOfEntry\": \"2022-03-15\",\n" +
						"    \"dateOfBirthday\": \"1984-02-27\",\n" +
						"    \"phone\": \"+375295811315\",\n" +
						"    \"email\": \"ars_1984@mail.ru\",\n" +
						"    \"login\": \"arse\",\n" +
						"    \"password\": \"parse\",\n" +
						"    \"role\": true,\n" +
						"    \"status\": true,\n" +
						"    \"department\": {\n" +
						"        \"id\": 1,\n" +
						"        \"name\": \"Отдел информационных технологий\"\n" +
						"    }\n" +
						"}").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}
}

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Retention(RetentionPolicy.RUNTIME)
@interface MockMvcTest {}