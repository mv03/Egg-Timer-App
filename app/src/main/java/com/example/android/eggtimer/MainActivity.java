package com.example.android.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    ImageView imageView;
    TextView textView;
    Button button;
    boolean counterIsActive=false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go!");
        counterIsActive=false;
    }

    public void timer(View view){

        if(counterIsActive){
            resetTimer();
        }else {

            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("STOP!");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsleft){
        int minutes=secondsleft/60;
        int seconds=secondsleft-(minutes*60);

        String secondString=Integer.toString(seconds);
        if(seconds <=9){
            secondString="0" + secondString;
        }

        textView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}