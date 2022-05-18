package com.example.gotouringv2;

import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    //FragmentManager fragmentManager = new FragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //an den yparxei to fragment container tote reutrn allios dimiourgei homeFragment
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null)
                return;
        }

        //dimiourgia me fragmentmanager kai gemisma me home fragment
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment).commit();



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
                        // menuItem.setChecked(true);
                        displayMessage("open insert");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_insert:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InsertFragment()).addToBackStack(null).commit();
                       // menuItem.setChecked(true);
                        displayMessage("open insert");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_delete:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeleteFragment()).addToBackStack(null).commit();
                      //displayMessage("open delete");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_update:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdateFragment()).addToBackStack(null).commit();
                       // menuItem.setChecked(true);
                        displayMessage("open update");
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