package com.verzeo.medicinemajorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity7 extends AppCompatActivity {

    ImageView home, cal, pro;
    CardView cardiologist, dentist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main7);


        home = findViewById(R.id.home2);
        cal = findViewById(R.id.calender2);
        pro = findViewById(R.id.user2);
        cardiologist = findViewById(R.id.cardio);
        dentist = findViewById(R.id.dental);

        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, doc_details.class);
                intent.putExtra("title", "CARDIOLOGY");
                startActivity(intent);

            }
        });

        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity7.this, doc_details.class);
                intent.putExtra("title", "DENTIST");
                startActivity(intent);
            }
        });

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity7.this, MainActivity2.class);
//                startActivity(intent);
//            }
//        });
//
//        cal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity7.this, MainActivity6.class);
//                startActivity(intent);
//            }
//        });
//
//
//        pro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity7.this, MainActivity5.class);
//                startActivity(intent);
//            }
//        });



    }
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this, MainActivity2.class);
//        startActivity(intent);
//    }
}