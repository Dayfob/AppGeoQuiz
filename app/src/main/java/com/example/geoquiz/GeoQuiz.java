package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_seasons, true),
            new Question(R.string.question_maths, false),
            new Question(R.string.question_geography, false),
            new Question(R.string.question_app, false),
            new Question(R.string.question_day, true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.textView_question);
        int question = mQuestionBank[mCurrentIndex].getmTextResId();

        mQuestionTextView.setText(question);


        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getmTextResId();
                mQuestionTextView.setText(question);
            }
        });


        mTrueButton = findViewById(R.id.button1);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.button2);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
    }


    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    public class Question {
        private int mTextResId;
        private boolean mAnswerTrue;

        public Question(int textResId, boolean answerTrue) {
            mTextResId = textResId;
            mAnswerTrue = answerTrue;
        }

        public int getmTextResId() {
            return mTextResId;
        }

        public void setmTextResId(int textResId) {
            mTextResId = textResId;
        }

        public boolean isAnswerTrue() {
            return mAnswerTrue;
        }

        public void setAnswerTrue(boolean answerTrue) {
            mAnswerTrue = answerTrue;
        }
    }
}