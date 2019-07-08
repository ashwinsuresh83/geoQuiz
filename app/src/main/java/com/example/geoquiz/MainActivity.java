package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX="index";
    private static final int REQUEST_CODE_CHEAT=0;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private boolean mIsCheater;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank=new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex=0;
    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean AnswerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(mIsCheater){
            messageResId=R.string.judgement_toast;
        }
        else {
            if (userPressedTrue == AnswerIsTrue)
                messageResId = R.string.correct_toast;
            else
                messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(MainActivity.this,messageResId,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start CheatActivity
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i=CheatActivity.newIntent(MainActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);

        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                mIsCheater=false;
                updateQuestion();
            }
        });
        updateQuestion();
        mTrueButton=(Button) findViewById(R.id.True_Button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });
        mFalseButton=(Button) findViewById(R.id.False_Button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

            }
        }
        );
        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);}

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK)
            return;
        if(requestCode==REQUEST_CODE_CHEAT){
            if(data==null)
                return;
            mIsCheater=CheatActivity.wasAnswerShown(data);
        }
    }
    @Override
    public void  onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    }

