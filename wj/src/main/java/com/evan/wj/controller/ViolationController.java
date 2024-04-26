package com.evan.wj.controller;

import com.evan.wj.pojo.Violation;
import com.evan.wj.service.ViolationService;

import com.evan.wj.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class ViolationController {
    @Autowired
    ViolationService violationService;

    @GetMapping("/api/violationAll")
    public List<Violation> list() throws Exception {
        return violationService.list();
    }

    @PostMapping("/api/violationAll")
    public Violation addOrUpdate(@RequestBody Violation violation) throws Exception {
        //System.out.println(history.getUsername());
        violationService.addOrUpdate(violation);
        return violation;
    }

    @PostMapping("/api/violation/delete")
    public void delete(@RequestBody Violation violation) throws Exception {
        violationService.deleteByUsername(violation.getUsername());
    }
    //@PostMapping("/api/{id}/rooms")
   /*public List<Room> getById(@PathVariable("id") int id) throws Exception {
       //roomService.get(id);
       return roomService.getById(id);
   }*/


    //@GetMapping("/api/find")
    @PostMapping("/api/violation")
    @CrossOrigin
    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {POST,GET,OPTIONS})
    //public List<Room> searchResult(@RequestParam("id") String id) {
    public List<Violation> searchResult(@RequestBody Violation request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        //System.out.println(request.getUsername());
        return violationService.Search(request.getUsername());

    }


}

