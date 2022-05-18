package com.example.gotouringv2.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TripInfo {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "TripInfo_City")
    private String City;

    @ColumnInfo (name = "TravelAgency_Country")
    private String country;

    @ColumnInfo (name = "TravelAgency_TripDuration")
    private String tripduration;

    @ColumnInfo (name = "TravelAgency_TripType")//Cruise flight etc
    private String triptype;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getTriptype() {
        return triptype;
    }

    public void setTriptype(String triptype) {
        this.triptype = triptype;
    }

    public String getTripduration() {
        return tripduration;
    }

    public void setTripduration(String tripduration) {
        this.tripduration = tripduration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
