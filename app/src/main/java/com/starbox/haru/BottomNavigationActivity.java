package com.starbox.haru;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

public class BottomNavigationActivity extends AppCompatActivity {


    private long Back_Key_before_Time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HaruFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_haru:
                    selectedFragment = new HaruFragment();
                    break;
                case R.id.navigation_community:
                    selectedFragment = new CommunityFragment();
                    break;
                case R.id.navigation_contents:
                    selectedFragment = new ContentsFragment();
                    break;
                case R.id.navigation_point:
                    selectedFragment = new PointFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };


    @Override
    public void onBackPressed(){
        long now = System.currentTimeMillis();

        long result = now - Back_Key_before_Time;

        if(result < 2000) {
            this.finish();
        } else {
            Toast.makeText(this,"한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
            Back_Key_before_Time = System.currentTimeMillis();
        }
    } // 두번 누르면 종료

}
