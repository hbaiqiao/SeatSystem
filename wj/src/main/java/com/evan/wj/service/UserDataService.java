package com.evan.wj.service;

import com.evan.wj.dao.RoomDAO;
import com.evan.wj.dao.UserDataDAO;
import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import com.evan.wj.pojo.Room;
import com.evan.wj.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDataService {
    @Autowired
    UserDataDAO userDataDAO;
    //@Autowired
    //CategoryService categoryService;

    public List<UserData> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "roomid");
        return userDataDAO.findAll(sort);
        //return userDataDAO.findAll();
    }
    @Transactional
    public void addOrUpdate(UserData userData) {
        userDataDAO.save(userData);
    }

    /*public void deleteById(int id) {
        roomDAO.deleteById(id);
    } */
    /*public Room getById(int id) {
        //Room r= roomDAO.findById(id).orElse(null);
        return roomDAO.findById(id);
    }*/
    public UserData Search(String username) {
        return userDataDAO.findByUsername(username);
    }

    public UserData Change(int roomid,String seatid) {
        return userDataDAO.findByRoomidAndSeatid(roomid,seatid);
    }
}

