package com.example.ql_khach_san.model;

public class Floor {
    private int id;
    private int name;
    private int rooms;

    public Floor(int id, int name, int rooms) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
    }

    public Floor(int name, int rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
