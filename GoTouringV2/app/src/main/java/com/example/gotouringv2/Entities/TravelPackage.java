package com.example.gotouringv2.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
        (tableName = "TravelPackage",
       primaryKeys = {"id","Agency_Id","Trip_Id"},
        foreignKeys = {
        @ForeignKey(entity=TravelAgency.class,
                        parentColumns="id",
                        childColumns="Agency_Id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity=TripInfo.class,
                        parentColumns="id",
                        childColumns="Trip_Id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)})
public class TravelPackage {


    private int id;

    @ColumnInfo(name = "Agency_Id")
    private int AgencyId;

    @ColumnInfo (name = "Trip_Id")
    private int tripId;

    @ColumnInfo (name = "Departure_Date")
    private String departureDate;

    @ColumnInfo (name = "Package_price")
    private double price;

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
