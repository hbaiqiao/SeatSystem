package com.evan.wj.dao;
import com.evan.wj.pojo.Monitor;

import com.evan.wj.pojo.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitorDAO extends JpaRepository<Monitor,Integer> {

    Monitor findByRoomid(int roomid);


}
