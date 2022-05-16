package com.example.gotouring.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "TripPackage_Info")
data class TripPackage(
    @PrimaryKey (autoGenerate = true)
    val PackageId:Int=0,
    val agencyId: Int,
    val tripId:Int,
    @ColumnInfo(name = "DepartureDate")
    val DepartureDate:java.util.Date,
    @ColumnInfo(name = "Price")
    val Price:Double
)

