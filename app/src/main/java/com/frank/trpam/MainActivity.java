package com.frank.trpam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText Username, Email, Password, RePassword, PhoneNumber;
    private Button Daftar;
    private TextView Masuk;

    DatabaseReference  mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.Username);
        Email = findViewById(R.id.Email);
        Password =  findViewById(R.id.Password);
        RePassword = findViewById(R.id.Repassword);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        Masuk = findViewById(R.id.Masuk);
        Daftar = findViewById(R.id.Daftar);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Profile");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("User Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("TAG", "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("TAG", "Failed to read app title value.", error.toException());
            }
        }
        );

        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Login.class);
                startActivity(intent);
            }
        });

        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser(){
        double money = 0;
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String phone = PhoneNumber.getText().toString();
        String username = Username.getText().toString();
        String password2 = RePassword.getText().toString();


        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() >= 8 && password.equals(password2)){
            if(!password.isEmpty()){
                if (TextUtils.isEmpty(username)) {
                    username = mFirebaseDatabase.push().getKey();
                }
                User userData = new User( email,password, phone, username,money);
                mFirebaseDatabase.child(username).setValue(userData);
                Toast.makeText(MainActivity.this, R.string.error6, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , Login.class);
                startActivity(intent);
                finish();

            }else {
                Password.setError(getText(R.string.error4));
            }
        }else if(email.isEmpty()){
            Email.setError(getText(R.string.error4));
        }else if(!password.equals(password2)){
            Toast.makeText(MainActivity.this, R.string.error2, Toast.LENGTH_SHORT).show();
        }else if(password.length() < 8){
            Password.setError(getText(R.string.error3));
        }else if(phone.isEmpty()){
            PhoneNumber.setError(getText(R.string.error4));
        }
        else {
            Email.setError(getText(R.string.error5));
        }

    }
}