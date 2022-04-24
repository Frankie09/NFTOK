package com.frank.trpam;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Account extends AppCompatActivity {
    DatabaseReference mFirebaseDatabase;
    private Button btnChange;
    private String Username, Email, Password, Money, PhoneNumber;
    private EditText nomor,email,password;
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        this.getSupportActionBar().hide();
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Profile");
        Intent intent = getIntent();
        Username = intent.getStringExtra("username");
        Email = intent.getStringExtra("email");
        PhoneNumber = intent.getStringExtra("phone");
        Password = intent.getStringExtra("password");
        Money = intent.getStringExtra("money");
        btnChange = findViewById(R.id.btnChange);

        user = findViewById(R.id.username1);
        nomor = findViewById(R.id.editnomor);
        email = findViewById(R.id.editemail);
        password = findViewById(R.id.editpassword);
        user.setText("Username : "+ Username);
        nomor.setText(PhoneNumber);
        email.setText(Email);
        password.setText(Password);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() &&  !email.getText().toString().isEmpty() && !nomor.getText().toString().isEmpty() ){

                    mFirebaseDatabase.child(Username).child("phone").setValue(nomor.getText().toString());
                    mFirebaseDatabase.child(Username).child("email").setValue(email.getText().toString());
                    mFirebaseDatabase.child(Username).child("password").setValue(password.getText().toString());
                    Toast.makeText(Account.this, "Succes Change", Toast.LENGTH_SHORT).show();
                }  else if(email.getText().toString().isEmpty()){
                    email.setError(getText(R.string.error4));
                }   else if(nomor.getText().toString().isEmpty()){
                    nomor.setError(getText(R.string.error4));
                }
            }
        });


    }
}
