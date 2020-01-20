package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech myTTS;
    private SpeechRecognizer mySpeechRecognizer;
    private ImageView light;
    private TextView checking;
    private ImageButton micr;
    private Animation roting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        light = findViewById(R.id.light);
        micr = findViewById(R.id.micr);
        checking = findViewById(R.id.checking);
        roting = AnimationUtils.loadAnimation(this, R.anim.roting);

        micr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                light.startAnimation(roting);
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                mySpeechRecognizer.startListening(intent);
            }
        });
        initializeTextToSpeech();
        initializeSpeechRecognizer();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    0);
        }
        light.clearAnimation();
    }

    private void initializeSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            mySpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mySpeechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onResults(Bundle bundle) {
                    List<String> results = bundle.getStringArrayList(
                            SpeechRecognizer.RESULTS_RECOGNITION
                    );
                    assert results != null;
                    processResults(results.get(0));
                    Log.i("myLogs", String.valueOf(results));
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void processResults(String command) {
        Log.i("myLogs", command);
        String res = Objects.requireNonNull(processing(command.toLowerCase())).getOperation();
        speak(res);
    }

    private void initializeTextToSpeech() {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (myTTS.getEngines().size() == 0) {
                    Toast.makeText(MainActivity.this, "HI",
                            Toast.LENGTH_LONG).show();
                } else {
                    myTTS.setLanguage(Locale.ENGLISH);
                    speak("Hi, my name is Elsa. How can I help you?");
                }
            }
        });
    }

    private void speak(String message) {
        if (Build.VERSION.SDK_INT >= 21) {
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    private Response processing(String current_request){
        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new Response((long)1,(long)1,"Hello, I'am fine. What about you?", 1));
        responses.add(new Response((long)2,(long)1,"Yeah, I'am great. How are you?", 1));
        responses.add(new Response((long)3,(long)1,"What'up MAAAN!", 1));
        responses.add(new Response((long)4,(long)2,"I am just missing around)", 2));
        responses.add(new Response((long)5,(long)2,"I am doing my stuff", 2));
        responses.add(new Response((long)6,(long)2,"I am singing now", 2));
        responses.add(new Response((long)7,(long)3,"My name Lucy", 3));
        responses.add(new Response((long)8,(long)3,"My name Bac", 3));
        responses.add(new Response((long)9,(long)3,"My name Jessie", 3));
        ArrayList<Response> current_responses = new ArrayList<>();
        ArrayList<Request> requests = new ArrayList<>();
        ArrayList<String> st1 = new ArrayList<>();
        st1.add("how");
        st1.add("are");
        st1.add("you");
        ArrayList<Long> rr1 = new ArrayList<>();
        rr1.add((long)1);
        rr1.add((long)2);
        rr1.add((long)3);
        requests.add(new Request((long)1, st1, rr1));
        ArrayList<String> st2 = new ArrayList<>();
        st2.add("what");
        st2.add("name");
        ArrayList<Long> rr2 = new ArrayList<>();
        rr2.add((long)7);
        rr2.add((long)8);
        rr2.add((long)9);
        requests.add(new Request((long)3, st2, rr2));
        ArrayList<String> st3 = new ArrayList<>();
        st3.add("what");
        st3.add("are");
        st3.add("doing");
        ArrayList<Long> rr3 = new ArrayList<>();
        rr3.add((long)4);
        rr3.add((long)5);
        rr3.add((long)6);
        requests.add(new Request((long)2, st3, rr3));
        Request request = null;
        for (int i = 0; i < current_request.length(); i++) {
            if (current_request.charAt(i) > 32 && current_request.charAt(i) < 65) {
                char delChar = current_request.charAt(i);
                current_request = current_request.replace(delChar, ' ');
            }
        }
        String[] clear_request = current_request.split(" ");
        for (Request req: requests) {
            ArrayList<String> keyWords = req.getKeyWords();
            int count = 0;
            for (String word: keyWords) {
                for (String word2: clear_request) {
                    if(word.equals(word2)){
                        count++;
                        break;
                    }
                }
            }
            if(count == keyWords.size()){
                request = req;
                break;
            }
        }
        if (request == null){
            System.out.println("NOT FOUND!");
            return null;
        }
        for (Long id: request.getResponseIds()) {
            for (Response res: responses) {
                if(res.getResponseId().equals(id)){
                    current_responses.add(res);
                }
            }
        }
        Random rand = new Random();
        int index = rand.nextInt(current_responses.size());
        return current_responses.get(index);
    }
}
