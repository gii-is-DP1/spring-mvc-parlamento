package com.japarejo.springmvc.room;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private static final String ROOM_LISTING = "RoomsListing";
    private static final String ROOM_EDIT = "EditRoom";

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String listAllRooms(ModelMap model) {
        List<Room> allRooms = this.roomService.getAllRooms();
        model.put("rooms", allRooms);
        return ROOM_LISTING;
    }

    @GetMapping("/delete/{id}")
    public String removeRoom(@PathVariable("id") long id, ModelMap model) {
        String message;

        try {
            this.roomService.removeRoom(id);
            message = "Room "+id+" removed successfully";
        } catch (EmptyResultDataAccessException e) {
            message = "Room "+id+" does not exist";
        }
        model.put("message", message);
        model.put("messageType", "info");
        return listAllRooms(model);       
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable("id") long id, ModelMap model) {
        Room room = this.roomService.getRoom(id);
        if (room != null) {
            model.put("room", room);
            return ROOM_EDIT;
        } else {
            model.put("message", "The room "+ id+" does not exist");
            model.put("messageType", "info");
            return listAllRooms(model);
        }
    }

    @PostMapping("/edit/{id}")
    public String saveRoom(@PathVariable("id") long id, @Valid Room room, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return ROOM_EDIT;
        } else {
            Room roomToUpdate = this.roomService.getRoom(id);
            if (roomToUpdate != null) {
                // roomToUpdate.setActive(room.getActive());
                // roomToUpdate.setDescription(room.getDescription());
                BeanUtils.copyProperties(room, roomToUpdate, "id");
                this.roomService.save(roomToUpdate);
                model.put("message", "Room "+id+ "updated succesfully");
                return listAllRooms(model);
            } else {
                model.put("message", "Room "+id+ "does not exist");
                return listAllRooms(model);
            }
        }

    }

    @GetMapping("/create")
    public String createRoom(ModelMap model) {
        model.put("room", new Room());
        return ROOM_EDIT;
    }

    @PostMapping("/create")
    public String saveNewRoom(@Valid Room room, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return ROOM_EDIT;
        } else {
            Room newRoom = new Room();
            BeanUtils.copyProperties(room, newRoom, "id");
            Room createdRoom = this.roomService.save(newRoom);
            model.put("message", "Room with id "+createdRoom.getId()+ " created successfully");
            return listAllRooms(model);
        }
    }
}
