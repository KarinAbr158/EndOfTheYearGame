package com.example.endoftheyeargame;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScoreListDAO {
    @Insert
    void insert(ScoreList scoreList);

    @Update
    void update(ScoreList scoreList);

    @Delete
    void delete(ScoreList scoreList);

    @Query("SELECT * FROM scores")
    List<ScoreList> getAllScores();

    @Query("SELECT * FROM scores WHERE id = :id")
    ScoreList getScoreByID(int id);

    @Query("SELECT * FROM scores ORDER BY score DESC")
    List<ScoreList> getAllScoresSorted();
}
