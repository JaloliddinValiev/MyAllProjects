package com.kabobchi.muslimmol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class RunIntra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_intra);


        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent st=new Intent(RunIntra.this,RegisterUser.class);
                        startActivity(st);
                        finish();
                    }
                }, 1300);

    }
}