package com.japarejo.springmvc.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    public static final String ROOMS_LISTING="RoomsListing";

    @Autowired
    RoomService roomService;
    
     @GetMapping
     public ModelAndView showRoomsListing() {
         ModelAndView result=new ModelAndView(ROOMS_LISTING);
         result.addObject("rooms",roomService.getAllRooms());
         return result;
     }
}
