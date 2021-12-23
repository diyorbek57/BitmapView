package com.b12.lesson;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class ThirdActivity extends AppCompatActivity {

    ImageView imageView, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                converImageViewToBitmap();
            }
        });


    }

    void converImageViewToBitmap() {
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        imageView2.setImageBitmap(bitmap);
        Uri uri = getImageUri(this, bitmap);
        shareImageWith(uri);

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    void shareImageWith(Uri uriToImage) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share"));
    }
}