package com.example.sonido;

import androidx.appcompat.app.AppCompatActivity;
//import android.content.Context;
//import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
//import android.media.SoundPool;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

import java.io.IOException;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public Button bplay,bstop,bpausa, bint;
    private MediaPlayer mp,urlPlayer;
    //public SoundPool sp;
    //public int flujodemusica=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MediaController mediaController=new MediaController(this);

        bplay = (Button)findViewById(R.id.playMp);
        bstop = (Button)findViewById(R.id.stopMp);
        bpausa = (Button)findViewById(R.id.playSp);
        bint=(Button)findViewById(R.id.playMpInt);

        mp = MediaPlayer.create(this, R.raw.ines);

        mediaController.show();

        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        bint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = MediaPlayer.create(MainActivity.this, Uri.parse("http://www.all-birds.com/Sound/western%20bluebird.wav"));
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });

        bstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                try {
                    mp.prepare();
                }catch (IOException e){
                }
            }

        });

        bpausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });
    }
}