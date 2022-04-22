package com.frank.trpam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.InputStream;


public class Sell extends AppCompatActivity {
    ImageView upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        upload = findViewById(R.id.uploadgambar);

      upload.setOnClickListener(new View.OnClickListener() {
          @RequiresApi(api = Build.VERSION_CODES.M)
          @Override
          public void onClick(View view) {
              if (!checkStoragePermission()){
                  requestStoragePermission();
              }else{
                  PickImage();
              }
          }
      });
    }

    private void PickImage() {
        CropImage.activity().start(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
    }

    private boolean checkStoragePermission() {
        boolean res2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED;
        return res2;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/trpam-35771.appspot.com/o/tiga.jpg?alt=media&token=83ea26b1-18f9-4d06-abb9-5dfad79f363c").into(upload);



                try {
                    InputStream stream = getContentResolver().openInputStream(resultUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(stream);


                    System.out.println("pathnya adlah"+resultUri);
                    System.out.println("pathnya adlah"+stream);
                    System.out.println("pathnya adlah"+bitmap);





                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}