package com.evan.wj.controller;

import com.evan.wj.pojo.History;
import com.evan.wj.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@CrossOrigin
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping("/api/historyAll")
    public List<History> list() throws Exception {
        return historyService.list();
    }

    @PostMapping("/api/historyAll")
    public History addOrUpdate(@RequestBody History history) throws Exception {
        //System.out.println(history.getUsername());
        historyService.addOrUpdate(history);
        return history;
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
    @PostMapping("/api/history")
    @CrossOrigin
    //@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*",methods = {POST,GET,OPTIONS})
    //public List<Room> searchResult(@RequestParam("id") String id) {
    public List<History> searchResult(@RequestBody History request) {
        //response.setHeader("Access-Control-Allow-Origin", "*"); //
        //response.setHeader("Cache-Control","no-cache");       //
        //System.out.println(request.getUsername());
        return historyService.Search(request.getUsername());

    }


}

