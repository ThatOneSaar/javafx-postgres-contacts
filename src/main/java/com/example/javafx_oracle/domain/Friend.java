package com.example.javafx_oracle.domain;

public class Friend {
    private Integer id;
    private String name;
    private int phone;

    public Friend(Integer id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "ID: " + id + ";NAME: " + name + ";PHONE NUMBER: " + phone;
    }
}