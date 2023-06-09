package com.verzeo.medicinemajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity6 extends AppCompatActivity {

    ImageView home,hosp,me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main6);

        me = findViewById(R.id.user3);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity5();
            }
        });

        hosp = findViewById(R.id.hosp3);
        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsActivity();
            }
        });

        home = findViewById(R.id.home3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });


        ImageView buton = findViewById(R.id.back3);
        buton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openMainActivity2();

            }
        });}

    private void openMapsActivity() {
        Intent intent = new Intent(this, MainActivity7.class);
        startActivity(intent);
    }

    private void openMainActivity5() {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    private void openMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openMainActivity2();
    }
}