package com.example.gotouring

import androidx.annotation.WorkerThread
import com.example.gotouring.entities.Agency
import com.example.gotouring.entities.Trip
import com.example.gotouring.entities.TripPackage
import com.example.gotouring.entities.TripPackageDao
import kotlinx.coroutines.flow.Flow



class TripPackageRepository(private val tripPackageDao: TripPackageDao) {

    val allTripPackages: Flow<List<TripPackage>> = tripPackageDao.getTripPackages()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(tripPackage: TripPackage) {
        tripPackageDao.insert(tripPackage)
    }
    suspend fun insertTrip(trip: Trip) {
        tripPackageDao.insertTrip(trip)
    }

    suspend fun insertAgency(agency: Agency) {
        tripPackageDao.insertAgency(agency)
    }

    suspend fun update(agency: Agency) {
        tripPackageDao.update(agency)
    }
}