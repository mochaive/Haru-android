package com.starbox.haru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar setToolbar = findViewById(R.id.set_toolbar);
        setSupportActionBar(setToolbar);

        getSupportActionBar().setTitle("Settings");


        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SwitchPreference()).commit();


    }
}
