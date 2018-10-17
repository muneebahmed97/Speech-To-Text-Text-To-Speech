package com.example.muneeb.speech_to_text;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MyTextToSpeech extends AppCompatActivity {

    //Inititalizing Components
    EditText etEditText;
    SeekBar sbPitch, sbSpeed;
    Button btnStart;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        etEditText = (EditText) findViewById(R.id.et_editText);
        sbPitch = (SeekBar) findViewById(R.id.sb_pitch);
        sbSpeed = (SeekBar) findViewById(R.id.sb_speed);
        btnStart = (Button) findViewById(R.id.btn_start);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {//checking if the TextToSpeech is working and status is set
                    int result = textToSpeech.setLanguage(Locale.getDefault()) ;

                    //Checking whether the language is supported or not
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TextToSpeech", "No Language Support!");
                    } else {
                        btnStart.setEnabled(true);
                    }
                } else {
                    Log.e("TextToSpeech", "Inititalization Failed!");
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakNow();
            }
        });
    }

    private void speakNow() {
        String text = etEditText.getText().toString();
        float pitch = (float) sbPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;

        float speed = (float) sbSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
