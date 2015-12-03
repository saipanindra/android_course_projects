package com.example.saipa.mythreadingviewpost;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MyThreadingViewPost extends AppCompatActivity {
    private ImageView mImageView;
    private Bitmap mBitmap;
    private int mDelay = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_threading_view_post);
        mImageView = (ImageView)findViewById(R.id.imageView);
        final Button loadImageButton = (Button)findViewById(R.id.loadImage);
        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIcon();
            }
        });

        final Button supButton  = (Button)findViewById(R.id.sup);
        supButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyThreadingViewPost.this, "Sup bro!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void loadIcon() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(mDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable._android);
                mImageView.post(new Runnable() {
                    @Override
                    public void run() {
                       mImageView.setImageBitmap(mBitmap);
                    }
                });
            }
        }).start();


   }
}
