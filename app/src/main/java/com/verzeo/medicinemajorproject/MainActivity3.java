package com.verzeo.medicinemajorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.SimpleFormatter;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {


    private int notificationId = 1;

    TextView d1, setalarm;
    TextView t1;
    EditText nam, dos;
    int hour, Minute;
    String timeTonotify;
    DatePickerDialog.OnDateSetListener setListener;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth fauth;
//    private FirebaseUser fUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main3);


        fauth = FirebaseAuth.getInstance();


        t1 = findViewById(R.id.time);
        d1 = findViewById(R.id.date);
        nam = findViewById(R.id.name);
        dos =findViewById(R.id.dose);
        setalarm = findViewById(R.id.set);



    findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openMainActivity2();
                Toast.makeText(MainActivity3.this,"Canceled",Toast.LENGTH_SHORT).show();

            }
        });


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity3.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                        MainActivity3.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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


        setalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = nam.getText().toString().trim();
                String dose = dos.getText().toString().trim();
                String date = d1.getText().toString().trim();
                String time = t1.getText().toString().trim();
                {
                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("notificationId",notificationId);
                    intent.putExtra("title",name);
                    intent.putExtra("doses",dose);
                    intent.putExtra("time", date);
                    intent.putExtra("date", time);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity3.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                    String dateandtime = date + " " + timeTonotify;
                    SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.datetime));

                    try {
                        Date date1 = formatter.parse(dateandtime);
                        am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
                        Toast.makeText(getApplicationContext(), "Alarm", Toast.LENGTH_SHORT).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Intent intentBack = new Intent(getApplicationContext(), MainActivity2.class);
                    intentBack.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentBack);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    switch (view.getId()) {
                        case R.id.set:
                            Calendar startTime = Calendar.getInstance();
                            startTime.set(Calendar.HOUR_OF_DAY, hour);
                            startTime.set(Calendar.MINUTE, Minute);
                            startTime.set(Calendar.SECOND, 0);
                            long alarmStartTime = startTime.getTimeInMillis();

                            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                              Toast.makeText(MainActivity3.this,"Medicine Added Successfully",Toast.LENGTH_SHORT).show();
                            break;


                    }

                }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Medicine");


                String adr = ""+reference.child(fauth.getCurrentUser().getProviderId());
                //String phone = ""+reference.child(fauth.getCurrentUser().getPhoneNumber());
                UserHelperClass helperClass= new UserHelperClass(name,dose,date,time, adr);

                reference.child(fauth.getCurrentUser().getUid()).setValue(helperClass);



            }
        }
        );
    }

    private void openMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {



    }

    ;


}