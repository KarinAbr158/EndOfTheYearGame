package com.example.endoftheyeargame;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity3 extends AppCompatActivity {

    ListView listView;
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

        listView = findViewById(R.id.listView);
        database = ScoreListDatabase.getInstance(this);
        scoreListDAO = database.scoreListDAO();

        // Run DB query on a background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            List<ScoreList> scores = scoreListDAO.getAllScores();  // Run off main thread

            // Update UI on the main thread
            runOnUiThread(() -> {
                ArrayAdapter<ScoreList> adapter = new ArrayAdapter<>(
                        MainActivity3.this,
                        android.R.layout.simple_list_item_1,
                        scores
                );
                listView.setAdapter(adapter);
            });
        });
    }
}
