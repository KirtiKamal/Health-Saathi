package com.verzeo.medicinemajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class doc_details extends AppCompatActivity {

    private String[][] doc_cardio = {
            {"Dr. Ananya Pati", "Noamundi", "Exp: 10yrs", "Contact details: 9204898867","1000"},
            {"Dr. Dev Sasini", "Bangalore", "Exp: 5yrs", "Contact details: 9204890987","500"},
            {"Dr. Hena Soy", "Ranchi", "Exp: 3yrs", "Contact details: 9204798867", "800"},
            {"Dr. Nidhi Garg", "Bhubaneswar", "Exp: 6yrs", "Contact details: 9067972846", "600"},
            {"Dr. Sadhu Mishra", "Delhi", "Exp: 3yrs", "Contact details: 8092798867", "1000"},
            {"Dr. ", "Bhubaneswar", "Exp: 6yrs", "Contact details: 9067972846", "600"}

    };
    private String[][] doc_dentist = {
            {"Dr. Sangeeta Jena", "Noamundi", "Exp: 10yrs", "Contact details: 9060798624", "1000"},
            {"Dr. Shashank Chattar", "Gurugram", "Exp: 2yrs", "Contact details: 7635709754", "800"},
            {"Dr. Tania Chakraborty", "Kolkata", "Exp: 3yrs", "Contact details: 8092579864", "600"},
            {"Dr. Ananya Pati", "Noamundi", "Exp: 10yrs", "Contact details: 9204898867","1000"},
            {"Dr. Hena Soy", "Ranchi", "Exp: 3yrs", "Contact details: 9204798867", "800"},
            {"Dr. Nidhi Garg", "Bhubaneswar", "Exp: 6yrs", "Contact details: 9067972846", "600"}

    };


    String[][] doctor_details = {};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_doc_details);

        TextView tv = findViewById(R.id.doctor);
        Intent it = getIntent();
        String title =it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("CARDIOLOGY")==0) {
            doctor_details = doc_cardio;
        }
        else {
            doctor_details = doc_dentist;
        }


        list = new ArrayList();
        for(int i =0; i<doctor_details.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Visiting Fees:" + doctor_details[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4","line5"},
                new int[]{R.id.line_1, R.id.line_2, R.id.line_3, R.id.line_4, R.id.line_5,});


        ListView lst = findViewById(R.id.doc_list);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(doc_details.this, BookAppointment.class);
                intent.putExtra("text1",title);
                intent.putExtra("text2", doctor_details[position][0]);
                intent.putExtra("text3", doctor_details[position][1]);
                intent.putExtra("text4", doctor_details[position][3]);
                intent.putExtra("text5", doctor_details[position][4]);
                startActivity(intent);
            }
        });

        ImageView buton = findViewById(R.id.back2);
        buton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(doc_details.this, MainActivity7.class);
                startActivity(intent);

            }
        });
    }
}
