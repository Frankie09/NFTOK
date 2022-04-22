package com.frank.trpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class Market extends AppCompatActivity implements ListFrag.JudulListener {
    TextView article,judul_berita,penjual2,harga2;
    ImageView image;
    private String Username,status,money;
    Button playButton;
    DatabaseReference mFirebaseDatabase,userdb;


    private final int REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        article = (TextView) findViewById(R.id.article2);
        judul_berita = (TextView) findViewById(R.id.article);
        penjual2 = (TextView) findViewById(R.id.penjual);
        harga2 = (TextView) findViewById(R.id.hargaisi);
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Item");
        userdb = mFirebaseInstance.getReference("Profile");



        image = (ImageView) findViewById(R.id.imageView);

        FragmentManager manager = this.getSupportFragmentManager();

        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.fragment2))
                .show(manager.findFragmentById(R.id.fragment1))
                .commit();


    }


    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public void onJudulSelected(int index) {
        Intent intent = getIntent();
        Username = intent.getStringExtra("username");
        status = intent.getStringExtra("status");
        money = intent.getStringExtra("money");

        String status2 =  "koleksi";
        playButton = (Button) findViewById(R.id.tombolbeli);


        FragmentManager manager = this.getSupportFragmentManager();

        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.fragment1))
                .show(manager.findFragmentById(R.id.fragment2))
                .addToBackStack(null)
                .commit();

        if (status.equals(status2)) {

            playButton.setVisibility(View.GONE);

        }




        article.setText("Deskripsi : \n"+Ipsum.listData.get(index).getDeskripsi());
        judul_berita.setText(Ipsum.listData.get(index).getJudul());
        penjual2.setText("Penjual : \n"+Ipsum.listData.get(index).getPenjual());
        harga2.setText("Harga \n Rp."+Ipsum.listData.get(index).getHarga());

       // image.setImageResource(getImageId(this,Ipsum.listData.get(index).getFoto()));
        Picasso.with(this).load(getImageId(this,Ipsum.listData.get(index).getFoto())).into(image);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double b = Ipsum.listData.get(index).getHarga();
                double a = Double.parseDouble(money);
                if (a<b) {
                    Toast.makeText(Market.this, R.string.buyfail, Toast.LENGTH_SHORT).show();


                }else {
                    double c = a - b;
                    userdb.child(Username).child("money").setValue(c);

                    mFirebaseDatabase.child(Ipsum.listData.get(index).getFoto()).child("penjual").setValue(Username);
                    Toast.makeText(Market.this, R.string.buysucces, Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), Home.class);
                    intent2.putExtra("username",Username);
                    startActivity(intent2);

                }





            }
        });


    }




}