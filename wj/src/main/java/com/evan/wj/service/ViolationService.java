package com.evan.wj.service;
import com.evan.wj.dao.ViolationDAO;
import com.evan.wj.pojo.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ViolationService {
    @Autowired
    ViolationDAO violationDAO;
    //@Autowired
    //CategoryService categoryService;

    public List<Violation> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "roomid");
        return violationDAO.findAll(sort);
        //return userDataDAO.findAll();
    }
    @Transactional
    public void addOrUpdate(Violation violation) {
        violationDAO.save(violation);
    }

    /*public void deleteById(int id) {
        violationDAO.deleteById(id);
    } */


    @Transactional
    public void deleteByUsername(String username) {
        violationDAO.deleteByUsername(username);
    }
    /*public Room getById(int id) {
        //Room r= roomDAO.findById(id).orElse(null);
        return roomDAO.findById(id);
    }*/
    public List<Violation> Search(String username) {
        return violationDAO.findByUsername(username);
    }
}

