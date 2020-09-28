package com.kabobchi.hashmap;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private EditText edText1, edText2;
    private DatabaseReference root;
    private TextView txView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edText1 = findViewById(R.id.editText1);
        
        edText2 = findViewById(R.id.editText2);
        Button svButton = findViewById(R.id.button);
        Button ldButtob = findViewById(R.id.button33);
        final TextView txView1 = findViewById(R.id.textView11);
        txView2 = findViewById(R.id.textView22);

        root = FirebaseDatabase.getInstance().getReference().child("Users");

        ldButtob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                root.child("user1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Map<String,String> map = new ArrayMap<String, String>();
                            String id = map.get("ID");
                            String name = (String) map.get("Name");
                            txView1.setText(id);
                            txView2.setText(name);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        svButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edText1.getText().toString();
                String name = edText2.getText().toString();

                if(name.isEmpty()){
                    edText2.setError("ERROR");
                    return;
                }

                HashMap hashMap = new HashMap();
                hashMap.put("ID",id);
                hashMap.put("Name",name);
                root.child("User1").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,"Success", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}