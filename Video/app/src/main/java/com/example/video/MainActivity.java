package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.pm.ActivityInfo;
//import android.media.session.MediaController;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;
import android.widget.MediaController;
import android.media.MediaPlayer;
public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.setRequestedOrientation(
        //      ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        videoView = (VideoView) findViewById(R.id.videoView);

        mc = new MediaController(this);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);

        Uri path = Uri.parse("android.resource://com.example.video/" + R.raw.eldls);
        //Uri path = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");

        //Uri path = Uri.parse("rtsp://v4.cache1.c.youtube.com/CiILENy73wIaGQk4RDShYkdS1BMYDSANFEgGUgZ2aWRlb3MM/0/0/0/video.3gp");

        videoView.setVideoURI(path);
        videoView.requestFocus();
        videoView.start();


    }
}