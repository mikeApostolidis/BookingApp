package com.example.gotouring.entities

import androidx.room.*
import com.example.gotouring.entities.relations.AgencyWithTripPackages
import com.example.gotouring.entities.relations.TripWithTripPackages
import kotlinx.coroutines.flow.Flow

@Dao
interface TripPackageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgency(agency: Agency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: Trip)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tripPackage: TripPackage)

    @Update
    suspend fun update(tripPackage: TripPackage)

    @Update
    suspend fun update(trip: Trip)

    @Update
    suspend fun update(agency: Agency)

    @Delete
    suspend fun delete(agency: Agency)

    @Delete
    suspend fun delete(trip: Trip)

    @Delete
    suspend fun delete(tripPackage: TripPackage)

    @Query("SELECT * FROM TripPackage_Info ORDER BY DepartureDate ASC")
    fun getTripPackages():Flow<List<TripPackage>>

    @Query("SELECT * FROM Trip_info")
    suspend fun getTrips(): List <Trip>

    @Query("SELECT * FROM Agency")
    suspend fun getAgencies(): List <Agency>

    @Transaction
    @Query("SELECT * FROM TripPackage_Info WHERE agencyId = :agencyId")
    suspend fun getAgencyWithTripPackages(agencyId : Int): Flow<List<AgencyWithTripPackages>>

    @Transaction
    @Query("SELECT * FROM TripPackage_Info WHERE tripId = :tripId")
    suspend fun getTripWithTripPackages(tripId : Int): List<TripWithTripPackages>

    @Query("DELETE FROM TripPackage_Info")
    suspend fun deleteAll()




}