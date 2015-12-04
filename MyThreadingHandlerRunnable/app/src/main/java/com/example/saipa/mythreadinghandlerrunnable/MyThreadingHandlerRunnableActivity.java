package com.example.saipa.mythreadinghandlerrunnable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Handler;


import java.util.logging.LogRecord;

public class MyThreadingHandlerRunnableActivity extends AppCompatActivity {
    private int mDelay = 500;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private final Handler mHandler = new Handler();
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_threading_handler_runnable);


        mImageView = (ImageView)findViewById(R.id.imageView);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);


        Button loadImageButton = (Button)findViewById(R.id.loadimage);
        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new LoadIconThread(R.drawable._android)).start();

            }
        });
        Button supButton = (Button)findViewById(R.id.sup);
        supButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyThreadingHandlerRunnableActivity.this, "sup bro!", Toast.LENGTH_SHORT).show();
            }
        });
   }

    class LoadIconThread implements Runnable{

        int resid;
        LoadIconThread(int _resid)
        {
           resid = _resid;
        }
        @Override
        public void run() {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mImageView.setImageResource(android.R.color.transparent);
                }
            });
            mBitmap = BitmapFactory.decodeResource(getResources(), resid);
            for(int i = 1; i < 11 ; i++)
            {
                final int step = i;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(step * 10);
                    }
                });
                sleep();
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mImageView.setImageBitmap(mBitmap);
                }
            });

        }

        private void sleep() {
            try {
                Thread.sleep(mDelay);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
