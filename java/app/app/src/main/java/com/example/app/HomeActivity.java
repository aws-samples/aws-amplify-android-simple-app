package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.app.components.DashboardFragment;
import com.example.app.components.UserDataFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_navigation);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, new DashboardFragment());
        ft.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationItemSelected(item);
                return true;
            }

        });

    }

    private void navigationItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.bottom_navigation_search:

                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.frame_layout, new DashboardFragment());
                // or ft.add(R.id.your_placeholder, new ABCFragment());
                // Complete the changes added above
                ft.commit();

                break;

            case R.id.ic_qr_code:

                break;

            case R.id.bottom_navigation_profile:

                FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                ft3.replace(R.id.frame_layout, new UserDataFragment());
                ft3.commit();
                break;

            case R.id.bottom_navigation_settings:

                break;

        }
    }


}