package com.example.gotouringv2;

import android.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.gotouringv2.Entities.TravelGuideDatabase;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentContainer;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    //metavliti tipou klasis travelguidedatabase
    public static TravelGuideDatabase travelGuideDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dimiourgia db kai gemisma apo alli efarmogi
        //logika axristo
        travelGuideDatabase = Room.databaseBuilder(getApplicationContext(),TravelGuideDatabase.class,"TravelguideDB").allowMainThreadQueries().build();

        //an den yparxei to fragment container tote reutrn allios dimiourgei homeFragment
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }

        }

        //dimiourgia me fragmentmanager kai gemisma me home fragment
        HomeFragment homeFragment = new HomeFragment();
        //        CustomerManageFragment cm=new CustomerManageFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, cm).commit();



        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //insert_button = view.findViewById(R.id.nav_insert);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            //kanei to syrtari leioutgiko
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack(null).commit();
                        menuItem.setChecked(true);
                        displayMessage("open home");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_insertTA:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InsertFragment()).addToBackStack(null).commit();
                       // menuItem.setChecked(true);
                        displayMessage("open insert Travel Agency");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_insertTP:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InsertTPFragment()).addToBackStack(null).commit();
                        // menuItem.setChecked(true);
                        displayMessage("open insert Travel Package");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_insertTI:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InsertTIFragment()).addToBackStack(null).commit();
                        // menuItem.setChecked(true);
                        displayMessage("open insert Travel Info");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_updateTA:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).addToBackStack(null).commit();
                      // menuItem.setChecked(true);
                        displayMessage("open update Travel Agency");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_updateTP:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateTPFragment()).addToBackStack(null).commit();
                        // menuItem.setChecked(true);
                        displayMessage("open update Travel Package");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_updateTI:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateTIFragment()).addToBackStack(null).commit();
                        // menuItem.setChecked(true);
                        displayMessage("open update Travel Info");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_deleteTA:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteFragment()).addToBackStack(null).commit();
                        displayMessage("open delete Travel Agency");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_deleteTP:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteTPFragment()).addToBackStack(null).commit();
                        displayMessage("open delete Travel Package");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_deleteTI:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteTIFragment()).addToBackStack(null).commit();
                        displayMessage("open delete Travel Info");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_queryTA:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QueryFragment()).addToBackStack(null).commit();
                        displayMessage("open query Travel Agency");
                        drawerLayout.closeDrawers();
                        return true;
//pleon monop ena query xreiazetai alla ta afinw se comments just in case
             /*       case R.id.nav_queryTP:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QueryTPFragment()).addToBackStack(null).commit();
                        displayMessage("open query Travel Package");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_queryTI:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QueryTIFragment()).addToBackStack(null).commit();
                        displayMessage("open query Travel Info");
                        drawerLayout.closeDrawers();
                        return true; */
                    case R.id.nav_Customer_management:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CustomerManageFragment()).addToBackStack(null).commit();
                        displayMessage("open query Travel Info");
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });
        }
    void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


 //anoigei kleinei to syrtari
    @Override
   public  boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
           return true;
        }
        return super.onOptionsItemSelected(item);

    }

}