package com.evan.wj.controller;

import com.evan.wj.pojo.UserData;
import com.evan.wj.service.UserDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class UserDataController {
    @Autowired
    UserDataService userDataService;

    @GetMapping("/api/userdataAll")
    public List<UserData> list() throws Exception {
        return userDataService.list();
    }

    @PostMapping("/api/userdataAll")
    public UserData addOrUpdate(@RequestBody UserData userData) throws Exception {
        //System.out.println(userData.getUsername());
        userDataService.addOrUpdate(userData);
        return userData;
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
    @PostMapping("/api/userdata")
    @CrossOrigin
    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {POST,GET,OPTIONS})
    //public List<Room> searchResult(@RequestParam("id") String id) {
    public UserData searchResult(@RequestBody UserData request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        //System.out.println(request.getUsername());
        return userDataService.Search(request.getUsername());

    }
    @PostMapping("/api/userdataState")
    @CrossOrigin
    public UserData ChangeViolation(@RequestBody UserData request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        //System.out.println(request.getUsername());
        return userDataService.Change(request.getRoomid(), request.getSeatid());

    }


}

