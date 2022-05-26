package com.example.gotouringv2.Entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@Dao
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


// kanena apo ta tria den leitourgei
    @Query("select count(TP.Agency_Id) as MostWanted "+
    " from travelpackage TP "+
    " group by TP.Agency_Id "+
    " order by MostWanted desc")
    public List<Integer> getQueryMostAgencies();


    @Query("select count(TP.Trip_Id) as MostWanted "+
            " from travelpackage TP "+
            " group by TP.Trip_Id "+
            " order by MostWanted desc")
    public List<Integer> getQueryMostPickedTrip();

    @Query("select count(TP.Package_price) as Cheapest "+
            " from travelpackage TP "+
            " group by TP.Package_price"+
            " order by Cheapest desc")
    public List<Double> getQueryCheapestTrip();



}
