package com.example.gotouringv2.Entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TravelAgency.class,TripInfo.class,TravelPackage.class},version = 1)
public abstract class TravelGuideDatabase extends RoomDatabase {
    public abstract TravelGuideDao travelGuideDao();
}
