package com.evan.wj.service;

import com.evan.wj.dao.MonitorDAO;
import com.evan.wj.dao.UserDataDAO;

import com.evan.wj.pojo.Monitor;
import com.evan.wj.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MonitorService {
    @Autowired
    MonitorDAO monitorDAO;
    //@Autowired
    //CategoryService categoryService;

    public List<Monitor> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "roomid");
        return monitorDAO.findAll(sort);
        //return userDataDAO.findAll();
    }
    @Transactional
    public void addOrUpdate(Monitor monitor) {
        monitorDAO.save(monitor);
    }

    /*public void deleteById(int id) {
        roomDAO.deleteById(id);
    } */
    /*public Room getById(int id) {
        //Room r= roomDAO.findById(id).orElse(null);
        return roomDAO.findById(id);
    }*/
    public Monitor Search(int roomid) {
        return monitorDAO.findByRoomid(roomid);
    }
}

