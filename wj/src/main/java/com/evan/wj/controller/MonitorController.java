package com.evan.wj.controller;

import com.evan.wj.pojo.Monitor;
import com.evan.wj.pojo.UserData;
import com.evan.wj.service.MonitorService;
import com.evan.wj.service.UserDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class MonitorController {
    @Autowired
    MonitorService monitorService;

    @GetMapping("/api/monitor/all")
    public List<Monitor> list() throws Exception {
        return monitorService.list();
    }

    @PostMapping("/api/monitor/all")
    public Monitor addOrUpdate(@RequestBody Monitor monitor) throws Exception {
        System.out.println(" ");
        monitorService.addOrUpdate(monitor);
        return monitor;
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
    @PostMapping("/api/monitor")
    @CrossOrigin
    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {POST,GET,OPTIONS})
    //public List<Room> searchResult(@RequestParam("id") String id) {
    public Monitor searchResult(@RequestBody Monitor request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        System.out.println(request.getRoomid());
        return monitorService.Search(request.getRoomid());

    }


}

