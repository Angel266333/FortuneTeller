package com.angel266489.fortuneteller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

// Thanks for the tutorials and inspiration for some of the code from the user https://github.com/haroon47.
// All credits go to their respective owners.

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private static final int RC_SIGN_IN = 2600;
    private final static String KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(MainActivity.KEY)) {
            String data = bundle.getString(MainActivity.KEY);

            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "no data received", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_SIGN_IN) {
            String returnValue = data.getExtras().getString(KEY);
            Toast.makeText(this, returnValue, Toast.LENGTH_LONG).show();
        }
    }
}
