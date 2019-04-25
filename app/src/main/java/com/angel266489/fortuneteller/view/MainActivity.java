package com.angel266489.fortuneteller.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.angel266489.fortuneteller.R;
import com.angel266489.fortuneteller.database.Wish;
import com.angel266489.fortuneteller.database.WishDAO;
import com.angel266489.fortuneteller.database.WishDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


// Thanks for the tutorials and inspiration for some of the code from the user https://github.com/haroon47 for the fun roulette :D.
// All credits go to their respective owners.

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private List<Wish> wishes;
    private WishDAO dao;
    private static final int RC_SIGN_IN = 2600;
    private WishDatabase db;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private String email;
    private String firstName;
    private String lastName;
    private String mergedName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishes = new ArrayList<>();
        db = WishDatabase.getInstance(this);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(wishes);
        recyclerView.setAdapter(adapter);
        new GetAllWishesAsyncTask().execute();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_menu);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);

        TextView userLoggedIn = headerView.findViewById(R.id.userLoggedIn);
        TextView userEmail = headerView.findViewById(R.id.userEmail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(LoginActivity.KEY_FIRSTNAME)) {
            firstName = bundle.getString(LoginActivity.KEY_FIRSTNAME);
            lastName = bundle.getString(LoginActivity.KEY_LASTNAME);
            email = bundle.getString(LoginActivity.KEY_EMAIL);
            mergedName = firstName + " " + lastName;
            userLoggedIn.setText(mergedName);

            String email = bundle.getString(LoginActivity.KEY_EMAIL);
            userEmail.setText(email);

        } else {
            userLoggedIn.setText("Could not get user");
            userEmail.setText("Could not get email");
        }

        Toast userLoggedInToast = Toast.makeText(this, "Logged in as: " + mergedName, Toast.LENGTH_LONG);
        userLoggedInToast.show();
    }

    public List<Wish> getAllWishes() {
        dao = db.wishDAO();
        wishes = dao.getAll();
        return wishes;
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
            String returnValue = data.getExtras().getString(LoginActivity.KEY_EMAIL);
            Toast.makeText(this, "Logged in as: " + returnValue, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intentHome = new Intent(this, MainActivity.class);
                finish();
                startActivity(intentHome);
                return true;

            case R.id.wheel:
                Intent intentWheel = new Intent(this, RouletteActivity.class);
                intentWheel.putExtra(LoginActivity.KEY_EMAIL, email);
                startActivity(intentWheel);
                return true;

            case R.id.crystalball:
                Intent intentOnlineWish = new Intent(this, OnlineWishReaderActivity.class);
                startActivity(intentOnlineWish);
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetAllWishesAsyncTask().execute();
    }


    class GetAllWishesAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            dao = db.wishDAO();
            wishes = dao.getAll();

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    adapter.setWishes(wishes);
                }
            });


            Log.d("db", "Inserted" + wishes);

            return null;
        }
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMergedName() {
        return mergedName;
    }

    public void setMergedName(String mergedName) {
        this.mergedName = mergedName;
    }

}
