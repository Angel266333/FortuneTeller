package com.angel266489.fortuneteller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

// Thanks for the tutorials and inspiration for some of the code from the user https://github.com/haroon47.
// All credits go to their respective owners.

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private static final int RC_SIGN_IN = 2600;
    private final static String KEY = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mAuth = FirebaseAuth.getInstance();
        // Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_menu);
        navigationView.setNavigationItemSelectedListener(this);

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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toast.makeText(this, "TEST", Toast.LENGTH_LONG).show();
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(this, MainActivity.class);
                Toast.makeText(this, "You are already on the main page! :D", Toast.LENGTH_LONG).show();
                return true;

            case R.id.wheel:
                Intent intentWheel = new Intent(this, RouletteActivity.class);
                startActivity(intentWheel);
                return true;
        }
        return false;
    }
}
