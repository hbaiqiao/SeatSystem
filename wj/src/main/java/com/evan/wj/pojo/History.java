package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "history")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = true)
    int id;
    String username;
    int roomid;
    String seatid;
    String use_time;
    String use_state;
    int use_credit;
    String start_time;
    String date;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return  username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getSeatid() {
        return seatid;
    }

    public void setSeatid(String seatid) {
        this.seatid = seatid;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public String getUse_state() {
        return use_state;
    }

    public void setUse_state(String use_state) {
        this.use_state = use_state;
    }


    public int getUse_credit() {
        return use_credit;
    }

    public void setUse_credit(int use_credit) {
        this.use_credit = use_credit;
    }
    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


