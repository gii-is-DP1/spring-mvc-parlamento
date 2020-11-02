package com.japarejo.springmvc.controllers.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.japarejo.springmvc.model.entities.Sala;
import com.japarejo.springmvc.model.services.SalaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalaControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate(TestRestTemplate.HttpClientOption.ENABLE_COOKIES);	

	@Autowired
	SalaService salaService;
	
	@Before
	public void initialize() throws Exception {
		salaService.initializeSalas();
	}
	
	@Test
	public void testGetSalas() throws Exception {						
		ObjectMapper mapper = new ObjectMapper()
		   	      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ResponseEntity<String> response=restTemplate.getForEntity(createURLWithPort("salas"),String.class);
			assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
			ArrayList<Sala> salas=mapper.readValue(response.getBody(), new TypeReference<ArrayList<Sala>>() {});	
			assertTrue(salas.size()>0);
	}
	
	@Test
	public  void testCrearSala() throws Exception{
		String desc=UUID.randomUUID().toString();
		Sala randomSala=new Sala();
		randomSala.setDescripcion(desc);
		randomSala.setActivo("1");
		ResponseEntity<Sala> salaResponse=restTemplate.postForEntity(createURLWithPort("salas"), randomSala, Sala.class);
		assertThat(salaResponse.getStatusCode(),is(equalTo(HttpStatus.CREATED)));
		assertTrue(salaResponse.getHeaders().containsKey("Location"));
		assertThat(randomSala.getDescripcion(),is(equalTo(salaResponse.getBody().getDescripcion())));
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:"+port+"/api/"+uri;
	}
	
}
