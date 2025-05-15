package com.example.endoftheyeargame;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    ListView listView;
    ScoreList[] scoreList;
    ScoreListDatabase database;
    ScoreListDAO scoreListDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database = ScoreListDatabase.getInstance(this);
        scoreListDAO = database.scoreListDAO();

        scoreList = new ScoreList[scoreListDAO.getAllScores().size()];
        for(int i = 0; i < scoreList.length; i++){
            scoreList[i] = scoreListDAO.getScoreByID(i);
        }

        listView = findViewById(R.id.listView);
        ArrayAdapter<ScoreList> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                scoreList
        );
        listView.setAdapter(adapter);
    }
}