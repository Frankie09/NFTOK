package com.frank.trpam;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private TextView txtViewRegister;
    private Button btnLogin;
    DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtViewRegister = findViewById(R.id.txtViewRegister);

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Profile");



        txtViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this , MainActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser(){
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if(!username.isEmpty()){
            if(!password.isEmpty()){
                Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);

                            if(passwordFromDB.equals(password)){
                                String emailFromDB = snapshot.child(username).child("email").getValue(String.class);
                                String phoneFromDB = snapshot.child(username).child("phone").getValue(String.class);
                                String usernameFromDB = snapshot.child(username).child ("username").getValue(String.class);
                                double moneyFromDB = snapshot.child(username).child("money").getValue(double.class);
                                String moneystring = Double.toString(moneyFromDB);

                                Intent intent2 = new Intent(getApplicationContext(), Home.class);
                                intent2.putExtra("username",usernameFromDB);
                                intent2.putExtra("email",emailFromDB);
                                intent2.putExtra("phone",phoneFromDB);
                                intent2.putExtra("money",moneystring);

                                startActivity(intent2);
                            }
                            else {
                                editTextPassword.setError(getText(R.string.error1));
                            }
                        }
                        else {
                            editTextUsername.setError(getText(R.string.error7));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }else {
                editTextPassword.setError(getText(R.string.error4));
            }
        }else if(username.isEmpty()){
            editTextUsername.setError(getText(R.string.error4));
        }

    }

}
