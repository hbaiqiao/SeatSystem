package com.evan.wj.dao;
import com.evan.wj.pojo.History;

import com.evan.wj.pojo.UserData;
import com.evan.wj.pojo.Violation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolationDAO extends JpaRepository<Violation,Integer> {
    //List<Book> findAllByCategory(Category category);
    //List<Room> findAllByIdLike(int keyword1);
    //List<Room> findByAllId(int id);
    List<Violation> findByUsername(String username);
    List<Violation> deleteByUsername(String username);
    //@Override
    //List<UserData> findAll();
}
