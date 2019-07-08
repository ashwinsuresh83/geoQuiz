package com.example.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    public void setTextResId(int TextResId) {
        this.mTextResId = TextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean AnswerTrue) {
        this.mAnswerTrue = AnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }


    public Question(int textResId,boolean answerTrue){
        mTextResId=textResId;
        mAnswerTrue=answerTrue;
    }


}
