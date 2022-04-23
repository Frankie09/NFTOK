package com.frank.trpam;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {


    private String username,money,phone,email;
    private Button  btnLogout;
    private FirebaseAuth auth;
    DatabaseReference mFirebaseDatabase,item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Profile");
        item = mFirebaseInstance.getReference("Item");


        btnLogout = findViewById(R.id.btnLogout);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");

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
        data();
    }

    public void buy(View view){

        Ipsum.listData.clear();
        if(Ipsum.listData.isEmpty()){
            data2();
        }

        beli();
    }
    public void collection(View view){
        Ipsum.listData.clear();
        if(Ipsum.listData.isEmpty()){
            data3();
        }

        koleksi();

    }
    public void sell(View view){
        jual();
    }
    public void account(View view){
        akun();
    }
    public void about(View view){
        about();
    }

public  void data(){

    Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            String emaildb = snapshot.child(username).child("email").getValue(String.class);
            String phonedb = snapshot.child(username).child("phone").getValue(String.class);
            String passworddb = snapshot.child(username).child("password").getValue(String.class);
            String usernamedb = snapshot.child(username).child ("username").getValue(String.class);
            double moneydb = snapshot.child(username).child("money").getValue(double.class);
            String money2 = Double.toString(moneydb);
            Intent intent2 = new Intent(getApplicationContext(), Uang.class);
            intent2.putExtra("username",usernamedb);
            intent2.putExtra("email",emaildb);
            intent2.putExtra("phone",phonedb);
            intent2.putExtra("money",money2);
            intent2.putExtra("password",passworddb);


            startActivity(intent2);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}

    public void data2(){

        item.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    String nama = snapshot.child("penjual").getValue().toString();
                    String deskripsi = snapshot.child("deskripsi").getValue().toString();
                    String foto = snapshot.child("foto").getValue().toString();
                    String harga = snapshot.child("harga").getValue().toString();
                    String judul = snapshot.child("judul").getValue().toString();
                    String dari = snapshot.child("dari").getValue().toString();
                    String namafile = snapshot.child("namafile").getValue().toString();
                    String pemilik = snapshot.child("pemilik").getValue().toString();



                    Shoplist shoplist =  new Shoplist(judul,deskripsi,foto,nama,Double.parseDouble(harga),dari,namafile,pemilik);
                    Ipsum.listData.add(shoplist);

                    // mFirebaseDatabase.child(getResources().getStringArray(R.array.gambar)[i]).setValue(shoplist);







                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void data3(){
        Query checkUser = item.orderByChild("penjual").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    String nama = snapshot.child("penjual").getValue().toString();
                    String deskripsi = snapshot.child("deskripsi").getValue().toString();
                    String foto = snapshot.child("foto").getValue().toString();
                    String harga = snapshot.child("harga").getValue().toString();
                    String judul = snapshot.child("judul").getValue().toString();
                    String dari = snapshot.child("dari").getValue().toString();
                    String namafile = snapshot.child("namafile").getValue().toString();
                    String pemilik = snapshot.child("pemilik").getValue().toString();



                    Shoplist shoplist =  new Shoplist(judul,deskripsi,foto,nama,Double.parseDouble(harga),dari,namafile,pemilik);
                    Ipsum.listData.add(shoplist);

                    // mFirebaseDatabase.child(getResources().getStringArray(R.array.gambar)[i]).setValue(shoplist);







                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void beli(){

        Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String emaildb = snapshot.child(username).child("email").getValue(String.class);
                String phonedb = snapshot.child(username).child("phone").getValue(String.class);
                String passworddb = snapshot.child(username).child("password").getValue(String.class);
                String usernamedb = snapshot.child(username).child ("username").getValue(String.class);
                double moneydb = snapshot.child(username).child("money").getValue(double.class);
                String money2 = Double.toString(moneydb);
                Intent intent2 = new Intent(getApplicationContext(), Market.class);
                intent2.putExtra("username",usernamedb);
                intent2.putExtra("email",emaildb);
                intent2.putExtra("phone",phonedb);
                intent2.putExtra("money",money2);
                intent2.putExtra("password",passworddb);
                intent2.putExtra("status","beli");

                startActivity(intent2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void jual(){

        Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String emaildb = snapshot.child(username).child("email").getValue(String.class);
                String phonedb = snapshot.child(username).child("phone").getValue(String.class);
                String passworddb = snapshot.child(username).child("password").getValue(String.class);
                String usernamedb = snapshot.child(username).child ("username").getValue(String.class);
                double moneydb = snapshot.child(username).child("money").getValue(double.class);
                String money2 = Double.toString(moneydb);
                Intent intent2 = new Intent(getApplicationContext(), Sell.class);
                intent2.putExtra("username",usernamedb);
                intent2.putExtra("email",emaildb);
                intent2.putExtra("phone",phonedb);
                intent2.putExtra("money",money2);
                intent2.putExtra("password",passworddb);

                startActivity(intent2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void koleksi(){

        Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String emaildb = snapshot.child(username).child("email").getValue(String.class);
                String phonedb = snapshot.child(username).child("phone").getValue(String.class);
                String passworddb = snapshot.child(username).child("password").getValue(String.class);
                String usernamedb = snapshot.child(username).child ("username").getValue(String.class);
                double moneydb = snapshot.child(username).child("money").getValue(double.class);
                String money2 = Double.toString(moneydb);
                Intent intent2 = new Intent(getApplicationContext(), Market.class);
                intent2.putExtra("username",usernamedb);
                intent2.putExtra("email",emaildb);
                intent2.putExtra("phone",phonedb);
                intent2.putExtra("money",money2);
                intent2.putExtra("password",passworddb);
                intent2.putExtra("status","koleksi");

                startActivity(intent2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void about(){

        Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String emaildb = snapshot.child(username).child("email").getValue(String.class);
                String phonedb = snapshot.child(username).child("phone").getValue(String.class);
                String passworddb = snapshot.child(username).child("password").getValue(String.class);
                String usernamedb = snapshot.child(username).child ("username").getValue(String.class);
                double moneydb = snapshot.child(username).child("money").getValue(double.class);
                String money2 = Double.toString(moneydb);
                Intent intent2 = new Intent(getApplicationContext(), About.class);
                intent2.putExtra("username",usernamedb);
                intent2.putExtra("email",emaildb);
                intent2.putExtra("phone",phonedb);
                intent2.putExtra("money",money2);
                intent2.putExtra("password",passworddb);

                startActivity(intent2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void akun(){

        Query checkUser = mFirebaseDatabase.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String emaildb = snapshot.child(username).child("email").getValue(String.class);
                String phonedb = snapshot.child(username).child("phone").getValue(String.class);
                String passworddb = snapshot.child(username).child("password").getValue(String.class);
                String usernamedb = snapshot.child(username).child ("username").getValue(String.class);
                double moneydb = snapshot.child(username).child("money").getValue(double.class);
                String money2 = Double.toString(moneydb);
                Intent intent2 = new Intent(getApplicationContext(), Account.class);
                intent2.putExtra("username",usernamedb);
                intent2.putExtra("email",emaildb);
                intent2.putExtra("phone",phonedb);
                intent2.putExtra("money",money2);
                intent2.putExtra("password",passworddb);

                startActivity(intent2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}