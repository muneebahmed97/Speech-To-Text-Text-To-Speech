package com.example.muneeb.speech_to_text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnText; //Button declaration for TExt to Speech
    Button btnSpeech;   //Button declaration for Speech to Text
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnText = (Button) findViewById(R.id.btn_TextToSpeech);
        btnSpeech = (Button) findViewById(R.id.btn_SpeechToText);

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyTextToSpeech.class);
                startActivity(intent);
            }
        });

        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MySpeechToText.class);
                startActivity(intent);
            }
        });
    }
}
