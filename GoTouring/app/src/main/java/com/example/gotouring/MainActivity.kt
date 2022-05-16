package com.example.gotouring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gotouring.entities.Trip
import com.example.gotouring.entities.Agency
import com.example.gotouring.entities.TripPackage
import com.example.gotouring.entities.TripPackageDao
import com.google.android.material.navigation.NavigationView
import java.nio.channels.AsynchronousFileChannel.open

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RECYCLER VIEW  XRHSHMOPOIEITAI GIA NA PROSTHETEIS TRIP PACKAGES
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TripPackageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //DRAWER LAYOUT
        val drawerLayout :DrawerLayout =findViewById(R.id.drawer_layout)
        val navView :NavigationView=findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
           when(it.itemId){
               R.id.nav_home->Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
               R.id.nav_ChooseTripPackage->Toast.makeText(applicationContext,"Clicked Trip Packages",Toast.LENGTH_SHORT).show()
               R.id.nav_login->Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
           }

            true
        }
        /*val dao = TripPackageDatabase.getInstance(this).tripPackageDao

        val agencies = listOf(
            Agency(1,"Lolo","Koufitsa 24"),
            Agency(2,"Lolo2","Koufitsa 23"),
            Agency(3,"Lolo3","Koufitsa 30")
        )
        val trips = listOf(
            Trip(1,"Thessaloniki","Greece",5,"Cruise"),
            Trip(2,"Larissa","Greece",3,"Cruise")

        )

        val tripPackage=listOf(
            TripPackage(1,1,1,"20/2/2008",100.5),
            TripPackage(2,1,1,"20/2/2008",100.5),
        )*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}