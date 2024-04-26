package com.evan.wj.dao;
import com.evan.wj.pojo.History;

import com.evan.wj.pojo.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryDAO extends JpaRepository<History,Integer> {
    //List<Book> findAllByCategory(Category category);
    //List<Room> findAllByIdLike(int keyword1);
    //List<Room> findByAllId(int id);
    List<History> findByUsername(String username);

    //@Override
    //List<UserData> findAll();
}
