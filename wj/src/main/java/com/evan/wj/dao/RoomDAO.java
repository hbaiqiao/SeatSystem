package com.evan.wj.dao;

import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDAO extends JpaRepository<Room,Integer> {
    //List<Book> findAllByCategory(Category category);
    //List<Room> findAllByIdLike(int keyword1);
    //List<Room> findByAllId(int id);
    Room findById(int id);
}
