package com.example.gotouringv2.Entities;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

public interface TravelGuideDao {
    @Insert
    public void insertTravelAgency(TravelAgency travelAgency);

    @Insert
    public void insertTripInfo(TripInfo tripInfo);

    @Insert
    public void insertTravelPackage(TravelPackage travelPackage);

    @Delete
    public void deleteTravelAgency(TravelAgency travelAgency);

    @Delete
    public void deleteTripInfo(TripInfo tripInfo);

    @Delete
    public void deleteTravelPackage(TravelPackage travelPackage);

    @Update
    public void updateTravelAgency(TravelAgency travelAgency);

    @Update
    public void updateTripInfo(TripInfo tripInfo);

    @Update
    public void updateTravelPackage(TravelPackage travelPackage);

    @Query("select * from travelagency")
    public List<TravelAgency> getTravelAgencies();

    @Query("select * from tripinfo")
    public List<TripInfo> getTripInfos();

    @Query("select * from travelpackage")
    public List<TravelPackage> getTravelPackages();
}
