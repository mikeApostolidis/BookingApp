package com.example.gotouring

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TripPackageApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { TripPackageDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { TripPackageRepository(database.tripPackageDao) }
}