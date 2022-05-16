package com.example.gotouring.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gotouring.entities.Agency
import com.example.gotouring.entities.TripPackage

data class AgencyWithTripPackages(
    @Embedded val agency: Agency,
    @Relation(
        parentColumn = "agencyId",
        entityColumn = "agencyId",
    )
    val tripPackage: List<TripPackage>

)
