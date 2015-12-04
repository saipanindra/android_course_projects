package com.example.saipa.mythreadingasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MyThreadingAsyncTaskActivity extends AppCompatActivity {
    private int mDelay = 500;
    private Bitmap mBitmap;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_threading_async_task);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        final Button loadImageButton = (Button)findViewById(R.id.loadimg);
        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadIconTask().execute(R.drawable._android);
            }
        });

        final Button supButton = (Button)findViewById(R.id.sup);
        supButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyThreadingAsyncTaskActivity.this,"sup bro!", Toast.LENGTH_SHORT).show();

            }
        });
   }


    class LoadIconTask extends AsyncTask<Integer, Integer, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), params[0]);
            for(int i = 1 ; i < 11 ; i++)
            {
                sleep();
                publishProgress(i * 10);
            }
            return bmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap b)
        {
           mProgressBar.setVisibility(ProgressBar.INVISIBLE);
           mImageView.setImageBitmap(b);
        }

        private void sleep()
        {
            try
            {
                Thread.sleep(mDelay);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }



    }
}
