package com.japarejo.springmvc.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.japarejo.springmvc.configuration.SecurityConfiguration;

@WebMvcTest(controllers=RoomController.class,
    excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class)
public class RoomControllerTest {
    public static final String ID_ROOM_TO_EDIT = "1";    

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @WithMockUser
    @Test
    public void testRoomListing() throws Exception {
        mockMvc.perform(get("/rooms"))
            .andExpect(status().isOk())
            .andExpect(view().name("RoomsListing"))
            .andExpect(model().attributeExists("rooms"));

    }

    @WithMockUser
    @Test
    public void testRoomUpdateSuccesful() throws Exception {
        when(roomService.getRoomById(1)).thenReturn(new Room());
        mockMvc.perform(post("/rooms/edit/{id}", ID_ROOM_TO_EDIT).
            with(csrf()).
            param("active", "true").
            param("description", "En un lugar de la mancha")).            
        andExpect(status().isOk()).
        andExpect(view().name("RoomsListing")).
        andExpect(model().attributeExists("rooms"));

        verify(roomService).save(any(Room.class));
    }
}
