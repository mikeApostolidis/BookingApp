package com.example.gotouringv2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public  class TravelAgency {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "TravelAgency_name")
    private String name;

    @ColumnInfo (name = "TravelAgency_Address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
