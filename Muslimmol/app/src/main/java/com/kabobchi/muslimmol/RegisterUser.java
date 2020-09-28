package com.kabobchi.muslimmol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    private EditText mEdit;
    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_register_user);

        mEdit = findViewById(R.id.et_registr);
        mSend = findViewById(R.id.bt_send);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mEdit.getText().toString().trim();
                if(number.isEmpty() || number.length() < 12){
                    mEdit.setError("Write yout number");
                    mEdit.requestFocus();
                    return;
                }

                Intent intent = new Intent(RegisterUser.this,ConfirmRgs.class);
                intent.putExtra("phonenumber", number);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}