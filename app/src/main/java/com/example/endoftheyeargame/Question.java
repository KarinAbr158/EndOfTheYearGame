package com.example.endoftheyeargame;

public class Question {
    private String questionText;
    private String option1, option2, option3, option4;
    private int correctAnswerIndex; // 1-4

    public Question(String questionText, String option1, String option2, String option3, String option4, int correctAnswerIndex) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() { return questionText; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    public String getOption3() { return option3; }
    public String getOption4() { return option4; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }
}
