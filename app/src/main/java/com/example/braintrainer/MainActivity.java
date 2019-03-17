package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout gameLayout;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button startButton;
    Button playAgainButton;
    int locationOfCurrentAnswer;
    int score = 0;
    int units = 0;
    Boolean isActive = false;
    ArrayList<Integer> answer = new ArrayList<>();

    public void start (View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        isActive = true;

        playAgain(view);
    }

    public void generateQuestion () {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answer.clear();

        locationOfCurrentAnswer = random.nextInt(4);
        int wrongAnswer;

        for (int i =0; i < 4; i++) {
            if (i == locationOfCurrentAnswer) {
                answer.add(a+b);
            }

            wrongAnswer = random.nextInt(41);

            while (wrongAnswer == (a+b)) {
                wrongAnswer = random.nextInt(41);
            }

            answer.add(wrongAnswer);
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void chooseAnswer(View view) {
        if (isActive) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCurrentAnswer))) {
                score++;
                resultTextView.setText("Correct");

            } else {
                resultTextView.setText("Wrong!");
            }

            units++;
            generateQuestion();
            scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(units));
        }
    }

    public void playAgain(View view) {
        units = 0;
        score = 0;
        isActive = true;
        playAgainButton.setVisibility(View.INVISIBLE);
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your score: " + Integer.toString(score) + " / " + Integer.toString(units));
                playAgainButton.setVisibility(View.VISIBLE);
                isActive = false;
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.reultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sunTextView);
    }
}
