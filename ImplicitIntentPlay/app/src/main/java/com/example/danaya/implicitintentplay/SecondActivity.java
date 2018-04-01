package com.example.danaya.implicitintentplay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageview = (ImageView) findViewById(R.id.imageView);
//        Intent intent = getIntent();

        String path = getIntent().getStringExtra("imagePath");
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imageview.setImageBitmap(bitmap);

//        Bitmap bmp = (Bitmap) intent.getParcelableExtra("bmp_Image");
//        imageview.setImageBitmap(bmp);

    }
}
