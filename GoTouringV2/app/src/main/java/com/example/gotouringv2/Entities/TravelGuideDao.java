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

//Vres ton agent pou tha exei mono plane kai oxi bus LEITOURGEI
//MIN KSEXASIS NA DIMIOURGISEIS AGENT POU NA EXEI TAKSIDEPSI KAI ME AEROPLANO KAI ME LEOFORIO
    @Query(" select TA.TravelAgency_name " +
    " from TravelAgency TA inner join TravelPackage TP on TA.id=TP.Agency_id inner join TripInfo TI on TI.id=TP.Trip_Id" +
    " where TI.TravelAgency_TripType='Plane'" +
    " except " +
    " select TA.TravelAgency_name "+
    " from TravelAgency TA inner join TravelPackage TP on TA.id=TP.Agency_id inner join TripInfo TI on TI.id=TP.Trip_Id" +
    " where TI.TravelAgency_TripType='Bus'")
    public List<String> getAgentTraveledOnlyByPlane();

// Vres to id kai timi paketou pou tha parei leoforio LEITOURGEI
   @Query(" select TP.id as field1, TP.Package_price as field2"+
          " from TravelPackage TP inner join TripInfo TI on TP.Trip_Id=TI.id " +
          " where TravelAgency_TripType='Bus' ")
    public List<ResultIntInt> getidAndPriceOnlyBus();

//vres tin dimofilis poli LEITOURGEI
    @Query("select TI.TripInfo_City as field1,count(TP.Trip_Id) as field2 "+
            " from TripInfo TI inner join TravelPackage TP on TI.id=TP.Trip_Id"+
            " group by TripInfo_City  "+
            " order by field2 desc" +
            " limit 1 ")
    public List<ResultStringInt> getQueryMostPickedCity();


//Emfanise tis times ton taksidion me aujousa seira.LEITOURGEI
    @Query("select min(TP.Package_price) as Cheapest "+
            " from TravelPackage TP "+
            " group by TP.Package_price")
    public List<Double> getQueryCheapestTrip();


//emfanise to praktorio pou tha taksidepsi stis 4/5/2022
    @Query(" select  TA.TravelAgency_name as field1,TP.Agency_Id as field2"+
          "  from TravelPackage TP inner join TravelAgency TA on TP.Agency_Id=TA.id "+
          "  where Departure_Date='4/5/2022' ")
    public List<ResultStringInt> getDate();

//Pio akrivio paketo
    @Query(" select max(TP.Package_price) "+
            " from TravelPackage TP ")
    public List<Double> getPrice();
//emfanise onoma kai id praktorioy pou ksekinaei apo J i j
    @Query(" select TravelAgency_name as field1,id as field2 " +
           " from TravelAgency " +
           " where TravelAgency_name like 'J%' ")
    public List<ResultStringInt> getidFromNames();
//emfanise thn pio dimofilis xwra kai poses fores yparxei
    @Query(" select TravelAgency_Country as field1, count(TravelAgency_Country) as field2 "+
           " from TripInfo " +
           " group by TravelAgency_Country "+
           " having count(TravelAgency_Country)>1 ")
    public List<ResultStringInt> getxwra();


//emfanise meso oro tou kathe paketou
    @Query(" select avg(TP.Package_price) as AveragePacketPrice"+
           " from TravelPackage TP")
    public List<Double> getAVGPackagePrice();

}
