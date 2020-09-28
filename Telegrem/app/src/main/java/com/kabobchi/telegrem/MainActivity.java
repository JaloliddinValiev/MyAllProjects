package com.kabobchi.telegrem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kabobchi.telegrem.models.UserModel;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ImageView ava;
    private TextView namep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(this,RegisterActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        ava = findViewById(R.id.profile_image);
        namep = findViewById(R.id.nameprofile);
        root.child("Users").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!snapshot.exists()){
                    Log.e("MANABUYERDA",snapshot.toString());
                    startActivity(new Intent(MainActivity.this,RegisterInfo.class));
                    finish();
                }else{

                    UserModel userModel = snapshot.getValue(UserModel.class);
                    if (userModel.getName()==null){
                        startActivity(new Intent(MainActivity.this,RegisterInfo.class));
                        finish();
                    }

                    Glide.with(MainActivity.this).load(userModel.getAvatar()).into(ava);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}