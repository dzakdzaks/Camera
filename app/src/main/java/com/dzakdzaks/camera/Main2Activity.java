package com.dzakdzaks.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static com.dzakdzaks.camera.MainActivity.BITMAP_SAMPLE_SIZE;

public class Main2Activity extends AppCompatActivity {
    String imgStoragePath, videoStoragePath;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imgPreview = (ImageView) findViewById(R.id.imgPreview);
        TextView txtDescription = (TextView) findViewById(R.id.txt_desc);
        VideoView videoPreview = (VideoView) findViewById(R.id.videoPreview);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoPreview);
        intent = getIntent();
        imgStoragePath = intent.getStringExtra("image");
        videoStoragePath = intent.getStringExtra("video");
        if (imgStoragePath != null && videoStoragePath == null) {
            txtDescription.setVisibility(View.GONE);
            videoPreview.setVisibility(View.GONE);
            imgPreview.setVisibility(View.VISIBLE);

            Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imgStoragePath);
            imgPreview.setImageBitmap(bitmap);
        } else {
            txtDescription.setVisibility(View.GONE);
            imgPreview.setVisibility(View.GONE);
            videoPreview.setVisibility(View.VISIBLE);

            videoPreview.setMediaController(mediaController);
            videoPreview.setVideoPath(videoStoragePath);
            videoPreview.requestFocus();
            // start playing
            videoPreview.start();
        }
    }
}
