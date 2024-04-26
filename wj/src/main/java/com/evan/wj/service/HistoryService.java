package com.evan.wj.service;

import com.evan.wj.dao.RoomDAO;
import com.evan.wj.dao.HistoryDAO;
import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import com.evan.wj.pojo.Room;
import com.evan.wj.pojo.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryDAO historyDAO;
    //@Autowired
    //CategoryService categoryService;

    public List<History> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "roomid");
        return historyDAO.findAll(sort);
        //return userDataDAO.findAll();
    }
    @Transactional
    public void addOrUpdate(History history) {
        historyDAO.save(history);
    }

    /*public void deleteById(int id) {
        roomDAO.deleteById(id);
    } */
    /*public Room getById(int id) {
        //Room r= roomDAO.findById(id).orElse(null);
        return roomDAO.findById(id);
    }*/
    public List<History> Search(String username) {
        return historyDAO.findByUsername(username);
    }
}

