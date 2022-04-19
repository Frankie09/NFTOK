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
    private EditText edittxtDeposit;
    private TextView txtviewMoney;
    private Button btnDeposit,btnHome;
    private String Username, Email, Password, Money, PhoneNumber;
    private  double amount;
    private boolean klik = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uang);

        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Profile");


        Intent intent = getIntent();
        Username = intent.getStringExtra("username");
        Email = intent.getStringExtra("email");
        PhoneNumber = intent.getStringExtra("phone");
        Password = intent.getStringExtra("password");
        Money = intent.getStringExtra("money");
        double moneydouble = Double.parseDouble(Money);

        edittxtDeposit = findViewById(R.id.edittxtDeposit);
        txtviewMoney = findViewById(R.id.txtviewMoney);
        btnDeposit = findViewById(R.id.btnDeposit);
        btnHome = findViewById(R.id.btnHome);

        txtviewMoney.setText("Rp. "+Money);

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edittxtDeposit.getText().toString().isEmpty()){
                    double depositdouble = Double.parseDouble(edittxtDeposit.getText().toString());
                    amount = moneydouble + depositdouble;
                    mFirebaseDatabase.child(Username).child("money").setValue(amount);
                    txtviewMoney.setText("Rp. "+Double.toString(amount));
                } else{
                    edittxtDeposit.setError(getText(R.string.error4));
                }

            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                klik = true;
                Intent intentback = new Intent(Uang.this, Home.class);
                intentback.putExtra("email", Email);
                intentback.putExtra("username", Username);
                intentback.putExtra("phone", PhoneNumber);
                intentback.putExtra("password", Password);
                if(!edittxtDeposit.getText().toString().isEmpty()){
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