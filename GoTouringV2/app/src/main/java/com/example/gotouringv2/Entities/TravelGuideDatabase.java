package com.example.gotouringv2.Entities;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TravelAgency.class,TripInfo.class,TravelPackage.class},version = 1)
public abstract class TravelGuideDatabase extends RoomDatabase {
  public abstract TravelGuideDao travelGuideDao();

  /*  static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE TravelPackage "
                    +"rename COLUMN Trip_Id ");
        }
    }; */

}
