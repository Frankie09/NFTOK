package com.frank.trpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Uang extends AppCompatActivity {
    DatabaseReference mFirebaseDatabase;
    private EditText isiuang;
    private TextView jumlauang;
    private Button tombolisi,btnHome;
    private String Username,  Money;
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

        Money = intent.getStringExtra("money");

        double angkakoma = Double.parseDouble(Money);

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
                    amount = angkakoma + depositdouble;
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

               data();

            }
        });


    }

    public void data(){
        Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(Username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String usernamedb = snapshot.child(Username).child ("username").getValue(String.class);
                Intent intent2 = new Intent(getApplicationContext(), Home.class);
                intent2.putExtra("username",usernamedb);
                startActivity(intent2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}