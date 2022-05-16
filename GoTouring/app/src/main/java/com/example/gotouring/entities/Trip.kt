package com.example.gotouring.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time


@Entity(tableName = "Trip_info")
data class Trip(
    @PrimaryKey (autoGenerate = true)
    val tripId:Int=0,
    @ColumnInfo(name = "City")
    val City:String,
    @ColumnInfo(name = "Country")
    val Country:String,
    @ColumnInfo(name = "Duration")
    val Duration:Int,//In days
    @ColumnInfo(name = "Type")
    val Type:String

    )
