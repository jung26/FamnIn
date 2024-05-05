package com.example.farmin;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    private NavigationView nav_view;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_board);
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Fragmenthome()).commit();
            nav_view.setCheckedItem(R.id.nav_home);
        }
//        menu = findViewById(R.id.menu);
//        llHome = findViewById(R.id.llHome);
//        llAbout = findViewById(R.id.llAbout);
//        llSettings = findViewById(R.id.llSettings);
//        llLogout = findViewById(R.id.llLogout);



//        menu.setOnClickListener(view -> {
//            openDrawer(drawerLayout);
//        });
//        llHome.setOnClickListener(view -> {
//            recreate();
//        });
//        llAbout.setOnClickListener(view -> {
//            redirectActivity(DashBoard.this, Info.class);
//        });
//        llSettings.setOnClickListener(view -> {
//            redirectActivity(DashBoard.this, Info.class);
//        });
//        llAbout.setOnClickListener(view -> {
//            FirebaseAuth.getInstance().signOut();
//            Intent intent = new Intent(DashBoard.this, SignIn.class);
//            startActivity(intent);
//        });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Fragmenthome()).commit();
        } else if (menuItem.getItemId() == R.id.nav_activity) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new FragmentActivity()).commit();
        } else if (menuItem.getItemId() == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Fragmetnabout()).commit();
        }  else if (menuItem.getItemId() == R.id.nav_setting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Fragmentsetting()).commit();
        } else  {
            Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}