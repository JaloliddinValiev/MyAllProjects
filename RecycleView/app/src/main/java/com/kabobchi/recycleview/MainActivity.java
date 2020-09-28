package com.kabobchi.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        initImageBitmap();
    }
    private void initImageBitmap(){
        Log.d(TAG, "initImageBitmap: preparing bitmaps.");



       

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");

        mImageUrls.add("https://talesbytheocean.files.wordpress.com/2020/05/pexels-photo-417074.jpeg");
        mNames.add("Lake");
        initRecyclerView();

    }
    private void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}