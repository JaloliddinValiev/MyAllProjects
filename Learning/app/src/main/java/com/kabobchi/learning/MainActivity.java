

package com.kabobchi.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import static com.kabobchi.learning.utils.NetworkUtils.generateURL;
import static com.kabobchi.learning.utils.NetworkUtils.getNames;

public class MainActivity extends AppCompatActivity {
    private EditText search;
    private Button searchbutton;
    private TextView javob;

    class VKTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });



            try {
                response = getNames(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String response){
            javob.setText(response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.searhField);
        searchbutton = findViewById(R.id.seach_vk);
        javob = findViewById(R.id.result);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                URL blaq = generateURL(search.getText().toString());
                new VKTask().execute(blaq);
            }
        };
        searchbutton.setOnClickListener(onClickListener);
    }
}