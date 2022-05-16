package com.example.gotouring.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gotouring.entities.Trip
import com.example.gotouring.entities.TripPackage

data class TripWithTripPackages(
    @Embedded val trip: Trip,
    @Relation(
        parentColumn = "tripId",
        entityColumn = "tripId",
    )
    val tripPackage: List<TripPackage>

)
