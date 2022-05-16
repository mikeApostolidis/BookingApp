package com.example.gotouring

import androidx.lifecycle.*
import com.example.gotouring.entities.Agency
import com.example.gotouring.entities.Trip
import com.example.gotouring.entities.TripPackage
import com.example.gotouring.entities.TripPackageDao
import kotlinx.coroutines.launch
import java.sql.Date

class TripPackageViewModel(private val repository: TripPackageRepository) : ViewModel() {

    val allTripPackages: LiveData<List<TripPackage>> = repository.allTripPackages.asLiveData()

    fun insert(tripPackage: TripPackage) = viewModelScope.launch {
        repository.insert(tripPackage)
    }
    private fun insertTrip(trip: Trip) {
        viewModelScope.launch {
            repository.insertTrip(trip)
        }
    }
    private fun insertAgency(agency: Agency) {
        viewModelScope.launch {
            repository.insertAgency(agency)
        }
    }
    private fun updateAgency(agency: Agency) {
        viewModelScope.launch {
            repository.update(agency)
        }
    }
    private fun getNewTripPackageEntry(agencyId:Int, tripId:Int, DepartureDate: Date,Price:Double): TripPackage {
        return TripPackage(
            agencyId=agencyId,
            tripId = tripId,
            DepartureDate = DepartureDate,
            Price = Price.toDouble()
        )
    }
    private fun getNewTripEntry(City:String, Country:String,Duration:Int,Type:String): Trip {
        return Trip(
           City = City,
           Country = Country,
           Duration = Duration,
           Type = Type
        )
    }

    private fun getNewAgencyEntry(name:String,Address:String): Agency {
        return Agency(
            name=name,
            Address=Address
        )
    }

    fun addNewTripPackage(agencyId:Int, tripId:Int, DepartureDate: Date,Price:Double) {
        val newTripPackage = getNewTripPackageEntry(agencyId, tripId, DepartureDate,Price)
        insertTripPackage(newTripPackage)
    }

    private fun insertTripPackage(newTripPackage: TripPackage) {

    }

    fun addNewTrip(City:String, Country:String,Duration:Int,Type:String) {
        val newTrip = getNewTripEntry(City, Country,Duration,Type)
        insertTrip(newTrip)
    }

    fun addNewAgency(name:String,Address:String) {
        val newAgency = getNewAgencyEntry(name,Address)
        insertAgency(newAgency)
    }


}

class TripPackageViewModelFactory(private val repository: TripPackageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripPackageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TripPackageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}