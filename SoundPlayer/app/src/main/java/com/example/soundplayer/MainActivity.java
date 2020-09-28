package com.example.soundplayer;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer oynaSound = MediaPlayer.create(this, R.raw.pitpit);
        Button boss = (Button) this .findViewById(R.id.button);
        boss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oynaSound .start(); 
            }
        });
    }
}