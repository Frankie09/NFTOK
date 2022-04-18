package com.frank.trpam;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {


    private String username,money,phone,email;
    private Button  btnLogout;
    private FirebaseAuth auth;
    DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}

        auth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");


        btnLogout = findViewById(R.id.btnLogout);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");
        money = intent.getStringExtra("money");


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intentlogout = new Intent(Home.this, Login.class);
                startActivity(intentlogout);
                finish();
            }
        });
    }

    public void money(View view){
        Intent intentdepo = new Intent(Home.this, Uang.class);

        intentdepo.putExtra("email", email);
        intentdepo.putExtra("username", username);
        intentdepo.putExtra("phone", phone);
        intentdepo.putExtra("money",money);

        startActivity(intentdepo);
    }

    public void buy(View view){
        Intent intentshoplist = new Intent(Home.this, Market.class);

        intentshoplist.putExtra("email", email);
        intentshoplist.putExtra("username", username);
        intentshoplist.putExtra("phone", phone);
        intentshoplist.putExtra("money",money);

        startActivity(intentshoplist);
    }
    public void collection(View view){
        Intent intentshoplist = new Intent(Home.this, Collection.class);

        intentshoplist.putExtra("email", email);
        intentshoplist.putExtra("username", username);
        intentshoplist.putExtra("phone", phone);
        intentshoplist.putExtra("money",money);

        startActivity(intentshoplist);
    }
    public void sell(View view){
        Intent intentshoplist = new Intent(Home.this, Collection.class);

        intentshoplist.putExtra("email", email);
        intentshoplist.putExtra("username", username);
        intentshoplist.putExtra("phone", phone);
        intentshoplist.putExtra("money",money);

        startActivity(intentshoplist);
    }
    public void account(View view){
        Intent intentshoplist = new Intent(Home.this, Account.class);

        intentshoplist.putExtra("email", email);
        intentshoplist.putExtra("username", username);
        intentshoplist.putExtra("phone", phone);
        intentshoplist.putExtra("money",money);

        startActivity(intentshoplist);
    }
    public void about(View view){
        Intent intentshoplist = new Intent(Home.this, About.class);

        intentshoplist.putExtra("email", email);
        intentshoplist.putExtra("username", username);
        intentshoplist.putExtra("phone", phone);
        intentshoplist.putExtra("money",money);

        startActivity(intentshoplist);
    }

}