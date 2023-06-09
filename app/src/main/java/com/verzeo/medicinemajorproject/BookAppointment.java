package com.verzeo.medicinemajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookAppointment extends AppCompatActivity {

    EditText fname, address, contact, pay;
    TextView d1, t1, book;
    DatePickerDialog.OnDateSetListener setListener;
    int hour, Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_book_appointment);

        ImageView backbutton = findViewById(R.id.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAppointment.this, doc_details.class);
                startActivity(intent);
            }
        });

        t1 = findViewById(R.id.time);
        d1 = findViewById(R.id.date);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookAppointment.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                d1.setText(date);
            }
        };

        t1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BookAppointment.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour = hourOfDay;
                                Minute = minute;
                                String time = hour + ":" + Minute;
                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm");
                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12hours = new SimpleDateFormat(
                                            "hh:mm a");
                                    t1.setText(f12hours.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, 12, 0, false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour, Minute);
                timePickerDialog.show();
            }
        });



        fname = findViewById(R.id.Fname);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.mobilenum);
        pay = findViewById(R.id.fee);
        book = findViewById(R.id.textbookappointment);

        fname.setKeyListener(null);
        address.setKeyListener(null);
        contact.setKeyListener(null);
        pay.setKeyListener(null);

        Intent intent =getIntent();
        String title = intent.getStringExtra("text1");
        String fullname = intent.getStringExtra("text2");
        String addressdetails = intent.getStringExtra("text3");
        String contactdetails = intent.getStringExtra("text4");
        String payment = intent.getStringExtra("text5");


        book.setText(title);
        fname.setText(fullname);
        address.setText(addressdetails);
        contact.setText(contactdetails);
        pay.setText("Cons Fees: "+payment+"/-");

        Button bookbtn = findViewById(R.id.bookappointment);
        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}