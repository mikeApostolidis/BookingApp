package com.example.gotouring

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gotouring.entities.Agency
import com.example.gotouring.entities.Trip
import com.example.gotouring.entities.TripPackage
import com.example.gotouring.entities.TripPackageDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.LocalDate

@Database(
    entities = [
        TripPackage::class,
        Trip::class,
        Agency::class,
    ],
    version = 1

)
abstract class TripPackageDatabase : RoomDatabase() {
    abstract  val tripPackageDao : TripPackageDao



        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: TripPackageDatabase? = null

            fun getDatabase(context: Context,scope:CoroutineScope): TripPackageDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TripPackageDatabase::class.java,
                        "word_database"
                    ).build()
                    INSTANCE = instance
                    //return instance
                    instance
                }
            }
        }


        private class TripPackageDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.tripPackageDao)
                    }
                }
            }

            suspend fun populateDatabase(tripPackageDao: TripPackageDao) {
                // Delete all content here.
                tripPackageDao.deleteAll()
                var agency =Agency(1,"The best agency","Solwnos 22")
                var trip=Trip(1,"Granada","Spain",30,"Flight")
                var date = Date.valueOf("22-2-2022")
                // Add sample words.
                var tp = TripPackage(1,1,1,date,32.1)
                tripPackageDao.insert(tp)
                tp = TripPackage(2,2,1,date,150.1)
                tripPackageDao.insert(tp)

                // TODO: Add your own words!
            }
        }

}
