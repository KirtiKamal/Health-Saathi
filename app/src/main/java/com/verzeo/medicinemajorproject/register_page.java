package com.verzeo.medicinemajorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class register_page extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private FirebaseAuth auth;
    TextView logbtn;
    EditText email, password,confpass, phonenum, dobdob, adhardetails, name;
    RadioButton radiobtnpgenderselected;
    RadioGroup radiogrpgender;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_page);

        email  = findViewById(R.id.emailid);
        password = findViewById(R.id.pass);
        confpass = findViewById(R.id.confirmpass);
        phonenum = findViewById(R.id.mobilenum);
        dobdob = findViewById(R.id.dob);
        adhardetails =findViewById(R.id.adhar);
        name = findViewById(R.id.Fname);
        logbtn = findViewById(R.id.loginbtn);

        radiogrpgender = findViewById(R.id.radiobtn);
        radiogrpgender.clearCheck();
        progressBar = findViewById(R.id.probar);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_page.this, login_page.class);
                startActivity(intent);
            }
        });



        Button btnreg = findViewById(R.id.registerbtn);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedgenderId = radiogrpgender.getCheckedRadioButtonId();
                radiobtnpgenderselected = findViewById(selectedgenderId);

                String textfname = name.getText().toString();
                String textmail = email.getText().toString();
                String dateob = dobdob.getText().toString();
                String passpass = password.getText().toString();
                String phone = phonenum.getText().toString();
                String adhar = adhardetails.getText().toString();
                String confirm = confpass.getText().toString();
                String textgender;

                if(TextUtils.isEmpty(textfname)){
                    //       Toast.makeText(register_page.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                    name.setError("Full name is Required");
                    name.requestFocus();

                }
                else if(TextUtils.isEmpty(textmail)){
                    //       Toast.makeText(register_page.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    email.setError("Email is Required");
                    email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(textmail).matches()){
                    //        Toast.makeText(register_page.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    email.setError("Valid Email is Required");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(dateob)){
                    //        Toast.makeText(register_page.this, "Please enter your date of birth", Toast.LENGTH_SHORT).show();
                    dobdob.setError("Date of Birth is Required");
                    dobdob.requestFocus();
                }
                else if(radiogrpgender.getCheckedRadioButtonId() == -1){
                    //        Toast.makeText(register_page.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    radiobtnpgenderselected.setError("Gender Selection is Required");
                    radiobtnpgenderselected.requestFocus();
                }
                else if(TextUtils.isEmpty(phone)){
                    //       Toast.makeText(register_page.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    phonenum.setError("Phone number is Required");
                    phonenum.requestFocus();
                }else if(phone.length()!=10){
                    //       Toast.makeText(register_page.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    phonenum.setError("Phone number should be 10 digit long");
                    phonenum.requestFocus();
                }else if(TextUtils.isEmpty(passpass)){
                    //        Toast.makeText(register_page.this, "Please create a password", Toast.LENGTH_SHORT).show();
                    password.setError("Password is Required");
                    password.requestFocus();
                }else if(TextUtils.isEmpty(confirm)){
                    //        Toast.makeText(register_page.this, "Please re-enter the password", Toast.LENGTH_SHORT).show();
                    confpass.setError("Confirm Password is Required");
                    confpass.requestFocus();
                }
                else if(passpass.length()<6){
                    //         Toast.makeText(register_page.this, "Password should be of at least 6 digits", Toast.LENGTH_SHORT).show();
                    password.setError("Password too weak");
                    password.requestFocus();
                }else if(!passpass.equals(confirm)){
                    //       Toast.makeText(register_page.this, "Please enter the same password", Toast.LENGTH_SHORT).show();
                    confpass.setError("Password doesn't match");
                    confpass.requestFocus();
                    password.clearComposingText();
                    confpass.clearComposingText();
                }
                else if(adhar.length()<12){
                    adhardetails.setError("This isn't an Adhar number");
                    adhardetails.requestFocus();

                }
                else{
                    textgender = radiobtnpgenderselected.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    registeruser(textfname, textmail, dateob, passpass, phone, adhar, confirm, textgender);

                }
            }
        });
    }

    private void registeruser(String textfname, String textmail, String dateob, String passpass, String phone, String adhar, String confirm, String textgender) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textmail, passpass).addOnCompleteListener(register_page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(register_page.this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();


                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("User");
                    UserHelperClassUser helperClassUser= new UserHelperClassUser(textfname, phone, adhar,textmail);
                    reference.child(auth.getCurrentUser().getUid()).setValue(helperClassUser);

                    Intent intent = new Intent(register_page.this, login_page.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}