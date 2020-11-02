package com.japarejo.springmvc.controllers.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.japarejo.springmvc.model.entities.Sala;
import com.japarejo.springmvc.model.repositories.SalaRepository;
import com.japarejo.springmvc.model.services.SalaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@WebMvcTest(SalaController.class)
public class SalaControllerTest {

   @MockBean
   private SalaRepository salaRepository;

   @Autowired
   private MockMvc mvc;
   
   @Before
   public void config() {
	   String desc="Salón de Plenos";
	   Sala salonDePlenos=new Sala();
	   salonDePlenos.setDescripcion(desc);
	   salonDePlenos.setActivo("1");
	   salonDePlenos.setId(1);
	   Sala sala=new Sala();	   		   	
	   	sala.setDescripcion("Sala de las traiciones");
	   	sala.setActivo("1");
	   List<Sala> salas=new ArrayList<Sala>();
	   salas.add(salonDePlenos);	   
	   Optional<Sala> salonOpcional=Optional.of(salonDePlenos);
	   Mockito.when(salaRepository.findByDescripcion(desc)).thenReturn(salonDePlenos);
	   Mockito.when(salaRepository.findAll()).thenReturn(salas);
	   Mockito.when(salaRepository.findById((long) 1)).thenReturn(salonOpcional);
	   Mockito.when(salaRepository.save(salonDePlenos)).thenReturn(salonDePlenos);
	   Mockito.when(salaRepository.save(sala)).thenReturn(sala);
   }
   
   @Test
   public void getSalas() throws Exception {

       mvc.perform(get("/api/salas")
               //.with(user("japarejo").password("jarl"))
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].descripcion", is("Salón de Plenos")));
   }

   @Test
   public void getSalaById() throws Exception {
       String desc="Salón de Plenos";       

       mvc.perform(get("/api/salas/1")               
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("descripcion", is(desc)))
               .andExpect(jsonPath("activo", is("1")));
   }
   
   
   @Test
   public void crearSala() throws Exception {
	   ObjectMapper mapper = new ObjectMapper()
		   	      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    Sala sala=new Sala();	   	
	   	String desc="Sala de las traiciones";
	   	sala.setDescripcion(desc);
	   	sala.setActivo("1");
	   	
	   	String content=mapper.writeValueAsString(sala);
	   	
	   	mvc.perform(post("/api/salas")
	   			   .content(content)
	               .contentType(APPLICATION_JSON))	   			   
	               .andExpect(status().isCreated())
	               .andExpect(header().exists("Location"))
	               .andExpect(jsonPath("descripcion", is(desc)))
	               .andExpect(jsonPath("activo", is("1")));
	   	
   }
   
   @Test
   public void crearSala2() throws Exception {
	   Sala sala=new Sala();
	   	ObjectMapper mapper = new ObjectMapper()
	   	      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	   	String desc="Salón de Plenos";
	   	sala.setDescripcion(desc);
	   	sala.setActivo("1");
	   	
	   	
	   	mvc.perform(post("/api/salas")
	   			   .content(mapper.writeValueAsString(sala))
	               .contentType(APPLICATION_JSON))	   			   
	               .andExpect(status().isBadRequest());
	   	
   }
   
}

