package com.frank.trpam;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Shoplist> {

    private final Context mContext;
    int mResource;

    public ListAdapter(Context context, int resource, ArrayList<Shoplist> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String judul = getItem(position).getJudul();
        double deskrip = getItem(position).getHarga();
        String gambar = getItem(position).getFoto();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent,false);

        TextView tvTitle = convertView.findViewById(R.id.nama);
        TextView tvArticle = convertView.findViewById(R.id.harga);

        ImageView gambar2 = convertView.findViewById(R.id.foto);

        if(judul.length() >= 10){
            tvTitle.setText(judul.substring(0,10)+"...");
        } else {
            tvTitle.setText(judul);
        }

            tvArticle.setText("Rp."+deskrip);

        gambar2.setImageResource(getImageId(mContext,gambar));



        return convertView;
    }
}