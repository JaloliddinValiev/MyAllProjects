package com.kabobchi.telegrem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterInfo extends AppCompatActivity {
    private ImageView mImage;
    private EditText mInfo;
    private FirebaseAuth fAuth;
    private Button mRegisterBtn;
    private FirebaseFirestore fStore;
    private DatabaseReference root;
    private final static int  IMAGEPICKER = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);
        root = FirebaseDatabase.getInstance().getReference();
        mImage = findViewById(R.id.profile);
        mInfo = findViewById(R.id.profilename);

        fAuth = FirebaseAuth.getInstance();
        mRegisterBtn = findViewById(R.id.registbutton);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picker = new Intent();
                picker.setType("image/*");
                picker.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(picker,"Surat tanla"),IMAGEPICKER);
            }
        });





        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mInfo.getText().toString();

                if(name.isEmpty()){
                    mInfo.setError("Write Your Name");
                    return;
                }
                root.child("Users").child(fAuth.getUid()).child("Name").setValue(name).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       startActivity(new Intent(RegisterInfo.this,MainActivity.class));
                       finish();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterInfo.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGEPICKER && data!=null && data.getData()!=null){
            // profile photo picked
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            final StorageReference urlPath = storageReference.child("UserPhotos").child(fAuth.getUid()+".jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                final Bitmap bitmap;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                   bitmap =  ImageDecoder.decodeBitmap(ImageDecoder.createSource(RegisterInfo.this.getContentResolver(), data.getData().normalizeScheme()));

                } else {
                   bitmap =  MediaStore.Images.Media.getBitmap(RegisterInfo.this.getContentResolver(), data.getData().normalizeScheme());
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG,90,baos);
                byte[] myBit = baos.toByteArray();
                UploadTask uploadTask = urlPath.putBytes(myBit);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        urlPath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                root.child("Users").child(fAuth.getUid()).child("avatar").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        mImage.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterInfo.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterInfo.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterInfo.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }catch (IOException e){
            }
        }
    }
}