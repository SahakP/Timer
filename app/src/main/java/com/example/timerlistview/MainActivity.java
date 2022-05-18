package com.example.timerlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    static final private int NUMBER_MINUTES = 0;
    static final private int NUMBER_SECOND = 1;
    private TextView textViewSecond;
    private TextView textViewMinutes;
    private TextView showTextView;
    private Button start;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        textViewSecond = (TextView) findViewById(R.id.textViewSecond);
        textViewMinutes = (TextView) findViewById(R.id.textViewMinutes);
        showTextView = (TextView) findViewById(R.id.showTextView);


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
                toStart();
            }


        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUMBER_MINUTES) {
            if (resultCode == RESULT_OK) {
                int number_minutes = data.getIntExtra(TimeSelectActivity.KEY, 0);
                textViewMinutes.setText(String.valueOf(number_minutes)+":min");
                count = number_minutes * 60;
            } else {
                textViewMinutes.setText("00");
            }
        }

        if (requestCode == NUMBER_SECOND) {
            if (resultCode == RESULT_OK) {
                int number_second = data.getIntExtra(TimeSelectActivity.KEY, 0);
                textViewSecond.setText(String.valueOf(number_second)+":sec");
                count += number_second;
            } else {
                textViewSecond.setText("00");
            }
        }
    }

        public void toStart () {

            new CountDownTimer(count*1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    showTextView.setText(millisUntilFinished / 1000 + ":sec");


                }

                public void onFinish() {
                    showTextView.setText("done!");
                }
            }.start();
        }


    }
