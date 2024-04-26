package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "violation")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Violation {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username",updatable = true)

    String username;
    int roomid;
    String seatid;


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


}


