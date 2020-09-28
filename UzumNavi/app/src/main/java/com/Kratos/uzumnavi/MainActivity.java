package com.Kratos.uzumnavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView rasm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.Button01);
        rasm = (ImageView) findViewById(R.id.view);
        Picasso.get().load(R.drawable.rasmvachcha).into(rasm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


    }
    public void openActivity2(){
        Intent intent = new Intent(this, Sariq.class);
        startActivity(intent);
    }
}