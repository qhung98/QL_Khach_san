package com.example.ql_khach_san.model;

public class Staff {
    private int id;
    private String username;
    private String password;

    public Staff(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
