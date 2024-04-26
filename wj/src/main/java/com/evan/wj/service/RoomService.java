package com.evan.wj.service;

import com.evan.wj.dao.RoomDAO;
import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import com.evan.wj.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomDAO roomDAO;
    //@Autowired
    //CategoryService categoryService;

    public List<Room> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return roomDAO.findAll(sort);
    }
    @Transactional
    public void addOrUpdate(Room room) {
        roomDAO.save(room);
    }

    public void deleteById(int id) {
        roomDAO.deleteById(id);
    }
    /*public Room getById(int id) {
        //Room r= roomDAO.findById(id).orElse(null);
        return roomDAO.findById(id);
    }*/
    public Room Search(int id) {
        return roomDAO.findById(id);
    }
}

