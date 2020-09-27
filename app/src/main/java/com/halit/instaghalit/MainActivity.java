package com.halit.instaghalit;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionDrawerToggle;
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Navigation Drawer to be opened from right to left (minSdkVersion 17) // Add this code to manifest: <application android:supportsRtl="true">
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Layout Variables
        mDrawerLayout = findViewById(R.id.main_drawer_layout);
        mNavigationView = findViewById(R.id.main_nav_view);

        mActionDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionDrawerToggle);
        mActionDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Listener for navigation View
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home){
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId() == R.id.search){
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId() == R.id.camera){
                    Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId() == R.id.likes){
                    Toast.makeText(MainActivity.this, "Likes", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId() == R.id.profile){
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId() == R.id.log_out){
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                }

                return false;

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
