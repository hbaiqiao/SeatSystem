package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "room")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = true)
    int id;


    String seat_used;
    String seat_violation;
    String seat_leave;
    String empty_area;
    int empty_seat;
    String room_rows;
    String room_cols;
    int seat_num;



    public String getSeat_used() {
        return seat_used;
    }

    public void setSeat_used(String seat_used) {
        this.seat_used = seat_used;
    }

    public String getSeat_violation() {
        return seat_violation;
    }

    public void setSeat_violation(String seat_violation) {
        this.seat_violation = seat_violation;
    }
    public String getSeat_leave() {
        return seat_leave;
    }

    public void setSeat_leave(String seat_leave) {
        this.seat_leave = seat_leave;
    }

    public String getEmpty_area() {
        return empty_area;
    }

    public void setEmpty_area(String empty_area) {
        this.empty_area = empty_area;
    }

    public int getEmpty_seat() {
        return empty_seat;
    }

    public void setEmpty_seat(int empty_seat) {
        this.empty_seat = empty_seat;
    }

    public String getRoom_rows() {
        return room_rows;
    }

    public void setRoom_rows(String room_rows) {
        this.room_rows = room_rows;
    }

    public String getRoom_cols() {
        return room_cols;
    }

    public void setRoom_cols(String room_cols) {
        this.room_cols = room_cols;
    }
    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


