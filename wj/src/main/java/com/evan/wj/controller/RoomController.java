package com.evan.wj.controller;

import com.evan.wj.pojo.Room;
import com.evan.wj.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/api/rooms")
    public List<Room> list() throws Exception {
        return roomService.list();
    }

    @PostMapping("/api/rooms")
    public Room addOrUpdate(@RequestBody Room room) throws Exception {
        //System.out.println(room.getRoom_rows());
        roomService.addOrUpdate(room);
        return room;
    }

   /* @PostMapping("/api/delete")
    public void delete(@RequestBody Room room) throws Exception {
        roomService.deleteById(room.getId());
    }*/
   //@PostMapping("/api/{id}/rooms")
   /*public List<Room> getById(@PathVariable("id") int id) throws Exception {
       //roomService.get(id);
       return roomService.getById(id);
   }*/


    //@GetMapping("/api/find")
    @PostMapping("/api/find")
    @CrossOrigin
    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {POST,GET,OPTIONS})
    //public List<Room> searchResult(@RequestParam("id") String id) {
    public Room searchResult(@RequestBody Room request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        //System.out.println(request.getId());
        return roomService.Search(request.getId());

    }


}

