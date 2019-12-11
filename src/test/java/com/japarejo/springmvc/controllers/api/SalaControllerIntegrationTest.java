package com.japarejo.springmvc.controllers.api;

import com.japarejo.springmvc.model.entities.Sala;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalaControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate(TestRestTemplate.HttpClientOption.ENABLE_COOKIES);	

	
	
	@Test
	public void testGetSalas() throws Exception {						
			ResponseEntity<String> response=restTemplate.getForEntity(createURLWithPort("salas"),String.class);
			assertThat(response.getStatusCode(),is(equalTo(HttpStatus.OK)));
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:"+port+"/api/";
	}
	
}
