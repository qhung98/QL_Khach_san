package com.example.ql_khach_san.model;

public class Room {
    private int id;
    private int name;
    private int type;
    private int empty;
    private int floor_id;

    public Room(int id, int name, int type, int empty, int floor_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.empty = empty;
        this.floor_id = floor_id;
    }

    public Room(int name, int type, int empty, int floor_id) {
        this.name = name;
        this.type = type;
        this.empty = empty;
        this.floor_id = floor_id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public int getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
    }
}
