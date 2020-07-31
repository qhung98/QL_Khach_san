package com.example.ql_khach_san.model;

public class Booking {
    private int id;
    private String customerName;
    private String timeIn;
    private String timeOut;
    private int type;
    private int sum;

    public Booking(int id, String customerName, String timeIn, String timeOut, int type, int sum) {
        this.id = id;
        this.customerName = customerName;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.type = type;
        this.sum = sum;
    }

    public Booking(String customerName, String timeIn, String timeOut, int type, int sum) {
        this.customerName = customerName;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.type = type;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
