package com.evan.wj.dao;
import com.evan.wj.pojo.Room;
import com.evan.wj.pojo.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataDAO extends JpaRepository<UserData,Integer> {
    //List<Book> findAllByCategory(Category category);
    //List<Room> findAllByIdLike(int keyword1);
    //List<Room> findByAllId(int id);
    UserData findByUsername(String username);
    UserData findByRoomidAndSeatid(int roomid,String seatid);
    //@Override
    //List<UserData> findAll();
}
