package com.example.gametrueorfalse;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout parent;
    TextView txtNumber1;
    TextView txtNumber2;
    TextView txtNumber3;
    TextView txtNumber4;
//    Button btnrandom;

    Button btnAnswerTrue;

    Button btnAnswerFalse;
    int sum = 0;
    int number3 = 0;
    int number1 = 0;
    int number2 = 0;

    private int countNumberOfTurns = 0;
    private static final long START_TIME_IN_MILLIS = 3000;
    private TextView txtCountdown;
    private Button btnStart;
    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountDownTimer = new CountDownTimer(3000, 10) {

            @Override
            public void onFinish() {
                mTimerRunning = false;
                btnStart.setText("Start");

            }

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
        };

        initViews();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartStart();
            }
        });

//        btnrandom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showSnackBar();
//                Random random = new Random();
////                 number1 = random.nextInt(100);
//                txtNumber1.setText(number1 + "");
//
////                 number2 = random.nextInt(100);
//                txtNumber2.setText(number2 + "");
//
////                 number3 = random.nextInt(100);
//                txtNumber3.setText(number3 + "");
//
//
//                sum = number1 + number2;
////                validateData();
////                Log.d(TAG, "fsdafdsf");
////                System.out.println("laplap");
//            }
//        });
        btnAnswerTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexNumber3 = Integer.parseInt(txtNumber3.getText().toString());
                if (indexNumber3 == sum) {
                    txtNumber4.setVisibility(View.VISIBLE);
                    txtNumber4.setTextColor(Color.GREEN);
                    restartStart();
                    if (countNumberOfTurns == 10) {
                        setWin();
                    }

                } else {
                    setLose();
                }
            }
        });

        btnAnswerFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexNumber3 = Integer.parseInt(txtNumber3.getText().toString());
                if (indexNumber3 != sum) {
                    txtNumber4.setVisibility(View.VISIBLE);
                    txtNumber4.setTextColor(Color.GREEN);
                    restartStart();
                    if (countNumberOfTurns == 10) {
                        setWin();

                    }
                } else {
                    setLose();
                }
            }
        });

    }

    private void setWin() {
        txtNumber4.setVisibility(View.VISIBLE);
        txtNumber4.setTextColor(Color.GREEN);
        txtNumber4.setText("WIN");
        mCountDownTimer.cancel();
        btnStart.setVisibility(View.VISIBLE);
        countNumberOfTurns = 0;
    }

    private void setLose() {
        txtNumber4.setVisibility(View.VISIBLE);
        txtNumber4.setTextColor(Color.RED);
        txtNumber4.setText("LOSE");
        mCountDownTimer.cancel();
        btnStart.setVisibility(View.VISIBLE);
        countNumberOfTurns = 0;
    }

    private void restartStart() {
        startTimer();
        showSnackBar();
        Random random = new Random();
        number1 = random.nextInt(100);
        txtNumber1.setText(number1 + "");

        number2 = random.nextInt(100);
        txtNumber2.setText(number2 + "");

        number3 = random.nextInt(100);
        txtNumber3.setText(number3 + "");
        sum = number1 + number2;
        countNumberOfTurns = countNumberOfTurns + 1;
        System.out.println("hjkhjkkj" + countNumberOfTurns);
    }

    private void initViews() {
        Log.d(TAG, "initView: Started");
        txtNumber1 = findViewById(R.id.txtNumber1);
        txtNumber2 = findViewById(R.id.txtNumber2);
        txtNumber3 = findViewById(R.id.txtNumber3);
        txtNumber4 = findViewById(R.id.txtNumber4);

//        btnrandom = findViewById(R.id.btnRandom);
        btnAnswerTrue = findViewById(R.id.btnAnswerTrue);
        btnAnswerFalse = findViewById(R.id.btnAnswerFalse);


        txtCountdown = findViewById(R.id.txtCountdown);
        btnStart = findViewById(R.id.btnStart);
    }

//    private void validateData() {
//        Log.d(TAG, "ValidateData: started");
//        int indexNumber3 = Integer.parseInt(txtNumber3.getText().toString());
//        if (indexNumber3 == sum) {
//            txtNumber4.setVisibility(View.VISIBLE);
//            txtNumber4.setTextColor(Color.GREEN);
//            txtNumber4.setText("WIN");
//            showSnackBar();
//        } else {
//            txtNumber4.setVisibility(View.VISIBLE);
//            txtNumber4.setTextColor(Color.RED);
//            txtNumber4.setText("LOSE");
////            showSnackBar();
//        }
//    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtNumber4.setVisibility(View.GONE);
    }

    private void startTimer() {
        btnStart.setVisibility(View.INVISIBLE);//mất đi nút start
//        mCountDownTimer.cancel();
        mCountDownTimer.start();
//        mTimerRunning = true;
//        btnStart.setText("pause");
    }


    //    tạ dừng đồng hồ
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btnStart.setText("Start");
    }

    private void updateCountDownText() {
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        txtCountdown.setText(timeLeftFormatted);

    }
}