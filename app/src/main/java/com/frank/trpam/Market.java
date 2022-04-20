package com.frank.trpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class Market extends AppCompatActivity implements ListFrag.JudulListener {
    TextView article,judul_berita,penjual2,harga2;
    ImageView image;
    DatabaseReference mFirebaseDatabase;


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

        String [] data = getResources().getStringArray(R.array.berita);


        if(Ipsum.listData.isEmpty()){
            for(int i=0;i<data.length;i++){
                Shoplist shoplist =  new Shoplist(getResources().getStringArray(R.array.berita)[i],getResources().getStringArray(R.array.isi)[i],getResources().getStringArray(R.array.gambar)[i],getResources().getStringArray(R.array.penjual)[i],Double.parseDouble(getResources().getStringArray(R.array.harga)[i]));
                Ipsum.listData.add(shoplist);
                mFirebaseDatabase.child(getResources().getStringArray(R.array.gambar)[i]).setValue(shoplist);

            }}




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
        String [] isi = getResources().getStringArray(R.array.isi);
        String [] judul = getResources().getStringArray(R.array.berita);
        String [] image2 = getResources().getStringArray(R.array.gambar);
        String [] penjual = getResources().getStringArray(R.array.penjual);
        String [] harga = getResources().getStringArray(R.array.harga);




        FragmentManager manager = this.getSupportFragmentManager();

        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.fragment1))
                .show(manager.findFragmentById(R.id.fragment2))
                .addToBackStack(null)
                .commit();

        article.setText("Deskripsi : \n"+isi[index]);
        judul_berita.setText(judul[index]);
        penjual2.setText("Penjual : \n"+penjual[index]);
        harga2.setText("Harga \n Rp."+harga[index]);

        image.setImageResource(getImageId(this,image2[index]));


    }


}