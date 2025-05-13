package com.example.endoftheyeargame;

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

public class MainActivity2 extends AppCompatActivity {
    ArrayList<Question> qList;
    TextView tv, rightNum, leftNum;
    Button bigger, smaller, equal, score;
    int scoreCnt, qIndex;
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
        bigger = findViewById(R.id.greaterBtn);
        smaller = findViewById(R.id.lessBtn);
        equal = findViewById(R.id.equalBtn);
        score = findViewById(R.id.scoreBtn);
        scoreCnt = 0;
        qIndex = 0;

        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String welcome = sharedPref.getString("user","");
        tv.setText(String.format("Hello %s", welcome));

        qList = populateQuestions(10);
        setQ(rightNum, leftNum);

        bigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question current = qList.get(qIndex);
                if ('>' == current.getCorrectAnswer()) {
                    scoreCnt++;
                    Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                qIndex++;
                if (qIndex < qList.size()) {
                    setQ(rightNum, leftNum);
                } else {
                    leftNum.setText("");
                    rightNum.setText("");
                    Toast.makeText(MainActivity2.this, "Game Over", Toast.LENGTH_SHORT).show();
                }
            }
        });
        smaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question current = qList.get(qIndex);
                if ('<' == current.getCorrectAnswer()) {
                    scoreCnt++;
                    Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                qIndex++;
                if (qIndex < qList.size()) {
                    setQ(rightNum, leftNum);
                } else {
                    leftNum.setText("");
                    rightNum.setText("");
                    Toast.makeText(MainActivity2.this, "Game Over", Toast.LENGTH_SHORT).show();
                }
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question current = qList.get(qIndex);
                if ('=' == current.getCorrectAnswer()) {
                    scoreCnt++;
                    Toast.makeText(MainActivity2.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                qIndex++;
                if (qIndex < qList.size()) {
                    setQ(rightNum, leftNum);
                } else {
                    leftNum.setText("");
                    rightNum.setText("");
                    Toast.makeText(MainActivity2.this, "Game Over", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<Question> populateQuestions(int numberOfQuestionsToCreate) {
        ArrayList<Question> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < numberOfQuestionsToCreate; i++) {
            int leftNum = r.nextInt(100);//Generates number in the range of 0-99
            int rightNum = r.nextInt(100);
            list.add(new Question(leftNum, rightNum));//Adds the new question to list
        }
        return list;
    }

    private void setQ(TextView right, TextView left) {
        Question current = qList.get(qIndex);
        left.setText(String.valueOf(current.getLeftValue()));
        right.setText(String.valueOf(current.getRightValue()));
    }
}