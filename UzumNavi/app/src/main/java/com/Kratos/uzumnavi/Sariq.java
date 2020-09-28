package com.Kratos.uzumnavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sariq extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sariq);
        button = (Button)findViewById(R.id.Button02);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity2(){
        Intent bluq = new Intent(this, MainActivity.class);
        startActivity(bluq);
    }
}