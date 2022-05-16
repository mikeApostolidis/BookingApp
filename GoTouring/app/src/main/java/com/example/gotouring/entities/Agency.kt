package com.example.gotouring.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Agency")
data class Agency(
    @PrimaryKey(autoGenerate = true)
    val agencyId: Int=0,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "address")
    val Address:String


    
)
