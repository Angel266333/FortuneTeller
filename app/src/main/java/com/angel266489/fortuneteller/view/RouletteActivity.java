package com.angel266489.fortuneteller.view;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.angel266489.fortuneteller.R;
import com.angel266489.fortuneteller.database.Wish;
import com.angel266489.fortuneteller.database.WishDatabase;

import java.util.Random;

public class RouletteActivity extends AppCompatActivity implements Animation.AnimationListener {

    private boolean blnButtonRotation = true;
    private int position;
    private long degrees = 0;
    private SharedPreferences sharedPrefererences;
    private String email;
    private WishDatabase db;

    ImageView selected, imageRoulette;
    Button b_start, b_up, b_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        db = WishDatabase.getInstance(this);

        email = (String) getIntent().getExtras().get(LoginActivity.KEY_EMAIL);

        b_start = (Button) findViewById(R.id.buttonStart);
        b_up = (Button) findViewById(R.id.buttonUp);
        b_down = (Button) findViewById(R.id.buttonDown);

        selected = (ImageView) findViewById(R.id.imageSelected);
        imageRoulette = (ImageView) findViewById(R.id.rouletteImage);

        sharedPrefererences = PreferenceManager.getDefaultSharedPreferences(this);
        this.position = this.sharedPrefererences.getInt("INT_NUMBER", 9);
        Log.d("Number", "Init " + position);
        setImageRoulette(this.position);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        resetPosition();
        this.blnButtonRotation = false;
        b_start.setVisibility(View.VISIBLE);
    }

    private void resetPosition() {
        imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_9));
        position = 9;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        position = calculateSpinnedNumber();
        String msg = convertNumberToFortune(position);
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_LONG).show();

        Log.d("Number", "" + position);

        Toast toastA = Toast.makeText(this, " " + position + " " + msg, Toast.LENGTH_SHORT);
        toastA.setGravity(49, 0, 0);
        toastA.show();

        this.blnButtonRotation = true;
        b_start.setVisibility(View.VISIBLE);
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_LONG).show();
        // save wish to db
        new SaveWishAsyncTask().execute(msg);
    }

    private int calculateSpinnedNumber() {
        return (int) (((double) this.position)
                - Math.floor(((double) this.degrees) / (360.0d / ((double) this.position))));
    }

    private String convertNumberToFortune(int number) {
        String msg = "";
        switch (number) {
            case 1:
                msg = getString(R.string.wishone);
                break;
            case 2:
                msg = getString(R.string.wishtwo);
                break;
            case 3:
                msg = getString(R.string.wishthree);
                break;
            case 4:
                msg = getString(R.string.wishfour);
                break;
            case 5:
                msg = getString(R.string.wishfive);
                break;
            case 6:
                msg = getString(R.string.wishsix);
                break;
            case 7:
                msg = getString(R.string.wishseven);
                break;
            case 8:
                msg = getString(R.string.wisheight);
                break;
            case 9:
                msg = getString(R.string.wishnine);
                break;
            case 10:
                msg = getString(R.string.wishten);
                break;
            default:
                break;
        }
        return msg;

    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View v) {
        if (this.blnButtonRotation) {
            degrees = 0;
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float) this.degrees, (float)
                    (this.degrees + ((long) ran)), 1, 0.5f, 1, 0.5f);

            this.degrees = (this.degrees + ((long) ran)) % 360;
            rotateAnimation.setDuration((long) ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);
        }
    }

    public void buttonUp(View v) {
        if (this.position < 10) {
            this.position++;
            setImageRoulette(this.position);
            b_down.setVisibility(View.VISIBLE);
            SharedPreferences.Editor editor = this.sharedPrefererences.edit();
            editor.putInt("INT_NUMBER", this.position);
            editor.commit();
        }

        if (this.position == 10) {
            b_up.setVisibility(View.INVISIBLE);
        }
    }

    public void buttonDown(View v) {
        if (this.position > 2) {
            position--;
            setImageRoulette(this.position);
            b_up.setVisibility(View.VISIBLE);

            SharedPreferences.Editor editor = this.sharedPrefererences.edit();
            editor.putInt("INT_NUMBER", this.position);
            editor.commit();
        }
        if (this.position > 2) {
            b_down.setVisibility(View.INVISIBLE);
        }
    }

    private void setImageRoulette(int myNumber) {
        switch (position) {
            case 1:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette));
                return;
            case 2:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_2));
                return;

            case 3:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_3));
                return;
            case 4:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_4));
                return;
            case 5:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_5));
                return;
            case 6:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_6));
                return;
            case 7:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_7));
                return;
            case 8:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_8));
                return;
            case 9:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_9));
                return;
            case 10:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_10));
                return;
        }
    }


    class SaveWishAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... wishes) {
            Wish wish = new Wish(wishes[0], email, false);
            db.wishDAO().insert(wish);
            return null;
        }
    }

}
