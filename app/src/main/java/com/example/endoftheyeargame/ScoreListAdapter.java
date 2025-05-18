package com.example.endoftheyeargame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreListAdapter extends BaseAdapter {

    private Context context;
    private List<ScoreList> scoreList;

    public ScoreListAdapter(Context context, List<ScoreList> scoreList) {
        this.context = context;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout if needed
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_layout_file, parent, false);
        }

        // Get the current ScoreList object
        ScoreList currentScore = scoreList.get(position);

        // Get views and set text
        TextView usernameView = convertView.findViewById(R.id.usernameTextView);
        TextView scoreView = convertView.findViewById(R.id.scoreTextView);

        usernameView.setText(currentScore.getUsername());
        scoreView.setText(String.valueOf(currentScore.getScore()) + "%");

        return convertView;
    }
}

