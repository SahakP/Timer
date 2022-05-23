package com.example.timerlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    static final private int NUMBER_MINUTES = 0;
    static final private int NUMBER_SECOND = 1;
    private TextView textViewSecond;
    private TextView textViewMinutes;
    public TextView showTextView;
    private Button start;
    private Button play;
    private Button pause;
    private ImageButton stop;
    private ImageView under_numbers;
    static int count;

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
        textViewSecond = (TextView) findViewById(R.id.textViewSecond);
        textViewMinutes = (TextView) findViewById(R.id.textViewMinutes);
        showTextView = (TextView) findViewById(R.id.showTextView);
        under_numbers = (ImageView)findViewById(R.id.under_numbers);


        textViewSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TimeSelectActivity.class);
                startActivityForResult(i, NUMBER_SECOND);
            }
        });

        textViewMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, TimeSelectActivity.class);
                startActivityForResult(i, NUMBER_MINUTES);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(MainActivity.this, StartedActivity.class);
                startActivityForResult(i, NUMBER_START);*/
                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);

                stop.setVisibility(View.VISIBLE);

                textViewMinutes.setVisibility(View.INVISIBLE);
                textViewSecond.setVisibility(View.INVISIBLE);
                under_numbers.setVisibility(View.INVISIBLE);
                toStart();
            }


        });
    }

    public void toStart() {

        CountDownTimer mCountDownTimer = new CountDownTimer(count * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                showTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                showTextView.setText("done!");
            }
        }.start();


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = showTextView.getText().toString();
                mCountDownTimer.cancel();
                showTextView.setText(str);
                pause.setVisibility(View.INVISIBLE);
                play.setVisibility(View.VISIBLE);
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = showTextView.getText().toString();
                count=Integer.parseInt(str);
                toStart();
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
            }
        });



        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                showTextView.setText("Stop");
                pause.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                textViewMinutes.setVisibility(View.VISIBLE);
                textViewSecond.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                under_numbers.setVisibility(View.VISIBLE);

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUMBER_MINUTES) {
            if (resultCode == RESULT_OK) {
                int number_minutes = data.getIntExtra(TimeSelectActivity.KEY, 0);
                textViewMinutes.setText(String.valueOf(number_minutes));
                count = number_minutes * 60;
            } else {
                textViewMinutes.setText("00");
            }
        }

        if (requestCode == NUMBER_SECOND) {
            if (resultCode == RESULT_OK) {
                int number_second = data.getIntExtra(TimeSelectActivity.KEY, 0);
                textViewSecond.setText(String.valueOf(number_second));
                count += number_second;
            } else {
                textViewSecond.setText("00");
            }
        }
    }


}
