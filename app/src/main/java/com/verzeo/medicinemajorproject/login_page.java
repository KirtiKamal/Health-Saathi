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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_page extends AppCompatActivity {

    EditText mail, password;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseUser user;
    Button orglog;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth fauth;
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);

//
        mail = findViewById(R.id.emailid);
        password = findViewById(R.id.pass);
        progressBar = findViewById(R.id.probar);
        orglog = findViewById(R.id.original_logbtn);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        TextView backbtn = findViewById(R.id.prevbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, register_page.class);
                startActivity(intent);
            }
        });

        orglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performlogin();
            }
        });
    }

    private void performlogin() {
        String textmail = mail.getText().toString();
        String passpass = password.getText().toString();


        if (TextUtils.isEmpty(textmail)) {
            //      Toast.makeText(login_page.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
            mail.setError("Email is Required");
            mail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(textmail).matches()) {
            //    Toast.makeText(login_page.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            mail.setError("Valid Email is Required");
            mail.requestFocus();
        } else if (TextUtils.isEmpty(passpass)) {
            //      Toast.makeText(login_page.this, "Please create a password", Toast.LENGTH_SHORT).show();
            password.setError("Password is Required");
            password.requestFocus();
        } else if (passpass.length() < 6) {
            //        Toast.makeText(login_page.this, "Password should be of at least 6 digits", Toast.LENGTH_SHORT).show();
            password.setError("Password too weak");
            password.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            auth.signInWithEmailAndPassword(textmail, passpass).addOnCompleteListener(login_page.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(login_page.this, "You are successfully logged in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login_page.this, MainActivity2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(login_page.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}