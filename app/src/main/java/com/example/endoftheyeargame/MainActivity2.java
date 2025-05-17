package com.example.endoftheyeargame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {

    TextView tv, rightNum, leftNum;
    Button biggerBtn, smallerBtn, equalBtn, scoreBtn;
    int scoreCnt;
    ScoreListDatabase database;
    ScoreListDAO scoreListDAO;
    Question current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Initializations
        rightNum = findViewById(R.id.rightTV);
        leftNum = findViewById(R.id.leftTV);
        tv = findViewById(R.id.textView);
        biggerBtn = findViewById(R.id.greaterBtn);
        smallerBtn = findViewById(R.id.lessBtn);
        equalBtn = findViewById(R.id.equalBtn);
        scoreBtn = findViewById(R.id.scoreBtn);
        scoreCnt = 0;
        database = ScoreListDatabase.getInstance(this);
        scoreListDAO = database.scoreListDAO();

        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String welcome = sharedPref.getString("user","");
        tv.setText(String.format("Hello %s", welcome));

        //to start the game
        current = generateQuestion();

        // Show the numbers on screen (assuming you have TextViews named leftNum and rightNum)
        leftNum.setText(String.valueOf(current.getLeftValue()));
        rightNum.setText(String.valueOf(current.getRightValue()));

        biggerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ('>' == current.getCorrectAnswer()) {
                    scoreCnt++;
                    Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

                // Generate next question
                current = generateQuestion();
                leftNum.setText(String.valueOf(current.getLeftValue()));
                rightNum.setText(String.valueOf(current.getRightValue()));
            }
        });

        smallerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ('<' == current.getCorrectAnswer()) {
                    scoreCnt++;
                    Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

                current = generateQuestion();
                leftNum.setText(String.valueOf(current.getLeftValue()));
                rightNum.setText(String.valueOf(current.getRightValue()));
            }
        });

        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ('=' == current.getCorrectAnswer()) {
                    scoreCnt++;
                    Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

                current = generateQuestion();
                leftNum.setText(String.valueOf(current.getLeftValue()));
                rightNum.setText(String.valueOf(current.getRightValue()));
            }
        });

        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = sharedPref.getString("user", "");
                ScoreList scoreList = new ScoreList(user, scoreCnt);

                // Run DB insert in background
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        scoreListDAO.insert(scoreList);

                        // Navigate to next activity on UI thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                                startActivity(i);
                            }
                        });
                    }
                });
            }
        });

    }

    public static Question generateQuestion() {
        Random r = new Random();
        int leftNum = r.nextInt(100);  // 0 to 99
        int rightNum = r.nextInt(100);
        return new Question(leftNum, rightNum);
    }
}