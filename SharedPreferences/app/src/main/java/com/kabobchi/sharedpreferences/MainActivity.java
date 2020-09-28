package com.kabobchi.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etText;
    Button btSave, btLoad;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = (EditText) findViewById(R.id.editT);

        btSave = (Button) findViewById(R.id.saveb);
        btSave.setOnClickListener(this);
        btLoad = (Button) findViewById(R.id.loadb);
        btLoad.setOnClickListener(this);
        loadText();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveb:
                saveText();
                break;
            case R.id.loadb:
                loadText();
                break;
            default:
                break; 
        }

    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        Toast.makeText(MainActivity.this,"Text loaded",Toast.LENGTH_SHORT).show();
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.commit();
        Toast.makeText(MainActivity.this, "Text Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}