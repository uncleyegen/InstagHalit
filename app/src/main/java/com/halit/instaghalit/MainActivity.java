package com.halit.instaghalit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Default fragment to be displayed
        changeFragmentDisplay(R.id.home);

        //listener for navigation view
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                changeFragmentDisplay(item.getItemId());
                return true;

            }
        });

    }

    private boolean changeFragmentDisplay(int item) {

        Fragment fragment = null;

        if (item == R.id.home){
            fragment = new HomeFragment();
            mDrawerLayout.closeDrawer(Gravity.START);

        }else if (item == R.id.search){
            fragment = new SearchFragment();
            mDrawerLayout.closeDrawer(Gravity.START);

        }else if (item == R.id.camera){
            fragment = new CameraFragment();
            mDrawerLayout.closeDrawer(Gravity.START);

        }else if (item == R.id.likes){
            fragment = new LikesFragment();
            mDrawerLayout.closeDrawer(Gravity.START);

        }else if (item == R.id.profile){
            fragment = new ProfileFragment();
            mDrawerLayout.closeDrawer(Gravity.START);

        }else if (item == R.id.log_out){

            SharedPrefrenceManger sharedPrefrenceManger = SharedPrefrenceManger.getInstance(getApplicationContext());
            sharedPrefrenceManger.logUserOut();

//            SECENEK 2
            /*
            SharedPrefrenceManger sharedPrefrenceManger = SharedPrefrenceManger.getInstance(getApplicationContext());
            if (sharedPrefrenceManger.isUserLogggedIn()) {
                sharedPrefrenceManger.logUserOut();
            }*/

        }

        if (fragment !=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment_content,fragment);
            ft.commit();
        }

        return false;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean isUserLoggedIn = SharedPrefrenceManger.getInstance(getApplicationContext()).isUserLogggedIn();

        if(!isUserLoggedIn){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }else{

        }
    }
}
