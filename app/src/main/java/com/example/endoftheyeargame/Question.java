package com.example.endoftheyeargame;

public class Question {
    private final int leftValue;
    private final int rightValue;

    //Constructor
    public Question(int lv, int rv) {
        this.leftValue = lv;
        this.rightValue = rv;
    }

    public int getLeftValue() {
        return this.leftValue;
    }

    public int getRightValue() {
        return this.rightValue;
    }

    //Returns '<', '>', or '=' based on the result
    public char getCorrectAnswer() {
        if (this.leftValue < this.rightValue) return '<';//If left is smaller returns '<'
        if (this.leftValue > this.rightValue) return '>';//If both are equal returns '>'
        return '=';//If left is greater returns '='
    }
}

