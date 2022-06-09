package com.example.timerlistview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Flow;


public class MainActivity extends AppCompatActivity {

    public TextView showTextView;
    public TextView textViewHour;
    public TextView textViewMinute;
    public TextView textViewSecond;
    public TextView textView7;
    public TextView textView;
    public TextView textView2;
    public TextView textView6;


    private Button start;
    private Button play;
    private Button pause;
    private ImageButton stop;
    private int count;
    private int selected_minute, selected_hour, selected_second;

    private NumberPicker minute;
    private NumberPicker hour;
    private NumberPicker second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        start = (Button) findViewById(R.id.startButton);
        play = (Button) findViewById(R.id.playButton);
        pause = (Button) findViewById(R.id.pauseButton);
        stop = (ImageButton) findViewById(R.id.stopImageButton);
        showTextView = (TextView) findViewById(R.id.showTextView);

        textViewHour = (TextView) findViewById(R.id.textViewHour);
        textViewMinute = (TextView) findViewById(R.id.textViewMinute);
        textViewSecond = (TextView) findViewById(R.id.textViewSecond);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView6 = (TextView) findViewById(R.id.textView6);


        second = (NumberPicker) findViewById(R.id.second);
        minute = (NumberPicker) findViewById(R.id.minute);
        hour = (NumberPicker) findViewById(R.id.hour);
        second.setMinValue(0);
        second.setMaxValue(59);
        minute.setMinValue(0);
        minute.setMaxValue(59);
        hour.setMinValue(0);
        hour.setMaxValue(23);
        second.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        hour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        minute.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        second.setOnValueChangedListener((picker, oldVal, newVal) -> {
            selected_second = newVal;
            textViewSecond.setText(String.valueOf(selected_second));

        });
        minute.setOnValueChangedListener((spinner, oldVal, newVal) -> {
            selected_minute = newVal;
            textViewMinute.setText(String.valueOf(selected_minute));

        });
        hour.setOnValueChangedListener((picker, oldVal, newVal) -> {
            selected_hour = newVal;
            textViewHour.setText(String.valueOf(selected_hour));
        });


        start.setOnClickListener(v -> {
            pause.setVisibility(View.VISIBLE);
            stop.setVisibility(View.VISIBLE);

            minute.setVisibility(View.INVISIBLE);
            second.setVisibility(View.INVISIBLE);
            hour.setVisibility(View.INVISIBLE);
            minute.setVisibility(View.INVISIBLE);
            start.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);

            count = selected_second + selected_minute * 60 + selected_hour * 3600;
            toStart();
        });
    }


    public void toStart() {

        CountDownTimer mCountDownTimer = new CountDownTimer(count * 1000L, 1000) {

            public void onTick(long millisUntilFinished) {

                if (selected_second == 0 && selected_minute > 0 && selected_hour > 0) {
                    selected_minute--;
                    selected_second = 59;
                } else {
                    if (selected_second == 0 && selected_minute == 0 && selected_hour > 0) {
                        selected_hour--;
                        selected_minute = 59;
                        selected_second = 59;
                    } else {
                        if (selected_second == 0 && selected_minute > 0 && selected_hour == 0) {
                            selected_minute--;
                            selected_second = 59;
                        } else {
                            selected_second--;
                        }
                    }
                }
                textViewHour.setText(String.valueOf(selected_hour));
                textViewMinute.setText(String.valueOf(selected_minute));
                textViewSecond.setText(String.valueOf(selected_second));


            }

            public void onFinish() {
                textView7.setVisibility(View.INVISIBLE);
                textView6.setVisibility(View.INVISIBLE);
                textViewHour.setVisibility(View.INVISIBLE);
                textViewMinute.setVisibility(View.INVISIBLE);
                textViewSecond.setVisibility(View.INVISIBLE);
                showTextView.setVisibility(View.VISIBLE);
                showTextView.setText("done!");

            }
        }.start();


        pause.setOnClickListener(v -> {
            String strHour = textViewHour.getText().toString();
            String strMinute = textViewMinute.getText().toString();
            String strSecond = textViewSecond.getText().toString();
            mCountDownTimer.cancel();
            textViewHour.setText(strHour);
            textViewMinute.setText(strMinute);
            textViewSecond.setText(strSecond);
            pause.setVisibility(View.INVISIBLE);
            play.setVisibility(View.VISIBLE);
        });


        play.setOnClickListener(v -> {
            String strHour = textViewHour.getText().toString();
            String strMinute = textViewMinute.getText().toString();
            String strSecond = textViewSecond.getText().toString();
            count = Integer.parseInt(strHour) * 3600 + Integer.parseInt(strMinute) * 60 + Integer.parseInt(strSecond);
            toStart();
            play.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);
        });


        stop.setOnClickListener(v -> {
            mCountDownTimer.cancel();
            textViewHour.setText("00");
            textViewMinute.setText("00");
            textViewSecond.setText("00");
            selected_second = 0;
            selected_hour = 0;
            selected_minute = 0;
            second.setValue(0);
            minute.setValue(0);
            hour.setValue(0);

            start.setVisibility(View.VISIBLE);
            textView6.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.VISIBLE);
            textViewHour.setVisibility(View.VISIBLE);
            textViewMinute.setVisibility(View.VISIBLE);
            textViewSecond.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            hour.setVisibility(View.VISIBLE);
            minute.setVisibility(View.VISIBLE);
            start.setVisibility(View.VISIBLE);
            second.setVisibility(View.VISIBLE);

            showTextView.setVisibility(View.INVISIBLE);
            play.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.INVISIBLE);
        });
    }
}





