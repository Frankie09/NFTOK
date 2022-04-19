package com.frank.trpam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Uang extends AppCompatActivity {
    DatabaseReference mFirebaseDatabase;
    private EditText isiuang;
    private TextView jumlauang;
    private Button tombolisi,btnHome;
    private String Username, Email, Password, Money, PhoneNumber;
    private  double amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uang);
        this.getSupportActionBar().hide();
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Profile");
        Intent intent = getIntent();
        Username = intent.getStringExtra("username");
        Email = intent.getStringExtra("email");
        PhoneNumber = intent.getStringExtra("phone");
        Password = intent.getStringExtra("password");
        Money = intent.getStringExtra("money");
        double moneydouble = Double.parseDouble(Money);

        isiuang = findViewById(R.id.isiuang);
        jumlauang = findViewById(R.id.jumlahuang);
        tombolisi = findViewById(R.id.tombolisi);
        btnHome = findViewById(R.id.btnHome);

        jumlauang.setText("Rp. "+Money);

        tombolisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isiuang.getText().toString().isEmpty()){
                    double depositdouble = Double.parseDouble(isiuang.getText().toString());
                    amount = moneydouble + depositdouble;
                    mFirebaseDatabase.child(Username).child("money").setValue(amount);
                    jumlauang.setText("Rp. "+Double.toString(amount));
                } else{
                    isiuang.setError(getText(R.string.error4));
                }

            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentback = new Intent(Uang.this, Home.class);
                intentback.putExtra("email", Email);
                intentback.putExtra("username", Username);
                intentback.putExtra("phone", PhoneNumber);
                intentback.putExtra("password", Password);
                if(!isiuang.getText().toString().isEmpty()){
                    intentback.putExtra("money",Double.toString(amount));
                } else{
                    intentback.putExtra("money",Money);
                }
                startActivity(intentback);
                finish();
            }
        });


    }
}